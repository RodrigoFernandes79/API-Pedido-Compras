package com.rodrigohf.apicompras.services;


import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rodrigohf.apicompras.domain.Cidade;
import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.domain.Endereco;
import com.rodrigohf.apicompras.domain.enums.PerfilClientes;
import com.rodrigohf.apicompras.domain.enums.TipoCliente;
import com.rodrigohf.apicompras.dtos.ClienteDTO;
import com.rodrigohf.apicompras.dtos.ClienteNewDTO;
import com.rodrigohf.apicompras.repositories.ClienteRepository;
import com.rodrigohf.apicompras.repositories.EnderecoRepository;
import com.rodrigohf.apicompras.security.UserSpringSecurity;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;
	@Value("${img.prefix.client.profile}")
	private String prefix;
	@Value("${img.profile.size}")
	private Integer sizeImg;
	
	

	public Cliente listarClientePorId(Long id) {
		
		//(Spring Security)Negando acesso para usuario acessar esse endpoint baseado nas seguintes condições:
				UserSpringSecurity user = UserService.authenticated();
				if (user==null || !user.hasRole(PerfilClientes.ADMIN) && !id.equals(user.getId())) {
					throw new InternalAuthenticationServiceException("Acesso Negado");
				}
		
		Optional<Cliente> obj = clienteRepo.findById(id);
		return obj
				.orElseThrow(()-> new RuntimeException("Objeto ID "+ id + " não Encontrado!!!"));
	}
	
	@Transactional
	public Cliente inserirCliente(Cliente cliente) {
		cliente.setId(null);
		Cliente obj = clienteRepo.save(cliente);
		enderecoRepo.saveAll(obj.getEnderecos());

		return obj;
	}

	public Cliente atualizarCliente(Long id, Cliente cliente) {
		
		
		Cliente find = clienteRepo.findByEmail(cliente.getEmail());
		if(find != null && find.getId()!= id) {    //se o email de outro cliente já existe no BD :
			throw new DataIntegrityViolationException("Email " +cliente.getEmail()+" já existe no Banco de dados");
		}
		return clienteRepo.findById(id).map(obj -> {
			obj.getId();
			obj.setNome(cliente.getNome());
			obj.setEmail(cliente.getEmail());

			
			Cliente cat = clienteRepo.save(obj);
			return cat;
		}).orElseThrow(() -> new RuntimeException("Objeto ID " + id + " não Encontrado!!!"));

	}

	public void deletarClientePorId(Long id) {

		try {
			clienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Objeto ID " + id + " não Encontrado!!!"));
			clienteRepo.deleteById(id);

		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException(
					"Objeto " + id + " Não pode ser apagado pois possui pedidos associados");
		}
	}

	public List<ClienteDTO> listarCliente() {

		List<Cliente> objList = clienteRepo.findAll();

		List<ClienteDTO> objListDTO = objList.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

		return objListDTO;

	}

	// Paginação com parâmetros opcionais na requisição(page= n° de paginas,
	// linesPerPage= qtdade de linhas por cada pág,
	// orderBy = ordenar por qual atributo(id ou nome), direction = ascendente ou
	// descendente)
	public Page<ClienteDTO> listarPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<Cliente> objList = clienteRepo.findAll(pageRequest);
		Page<ClienteDTO> objListDTO = objList.map(obj -> new ClienteDTO(obj));

		return objListDTO;
	}

	// método auxiliar para instanciar Cliente a partir do objeto ClienteDTO
	public Cliente fromDTO(ClienteDTO ClienteDTO) {
		return new Cliente(ClienteDTO.getId(), ClienteDTO.getNome(), ClienteDTO.getEmail(), null, null,null);
	}

	//Método auxiliar  para instanciar Cliente a partir dos dados do objeto ClienteNewDto(usar para criar novo cliente)
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(),clienteNewDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(clienteNewDTO.getTipo()), passwordEncoder.encode(clienteNewDTO.getSenha()));
		
		Cidade cid = new Cidade(clienteNewDTO.getCidadeId(),null,null);
		
		Endereco end = new Endereco(null,clienteNewDTO.getLogradouro(),clienteNewDTO.getNumero(),clienteNewDTO.getComplemento(),
				clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().add(clienteNewDTO.getTelefone1());
		if(clienteNewDTO.getTelefone2()!= null) {
			cli.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		if(clienteNewDTO.getTelefone3()!= null) {
			cli.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		return cli;
	}
	
	//enviando foto de perfil do cliente:
	public URI imagemDePerfilUpload(MultipartFile multipartFile) throws FileNotFoundException {
		UserSpringSecurity user = UserService.authenticated();
		if (user==null) {
			throw new InternalAuthenticationServiceException("Acesso Negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		//recortando e redimensionando a imagem:
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage,sizeImg);
		
		//gerando o nome do arquivo jpg:
		String fileName = prefix +user.getId()+".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
		
		
	}
	
	}


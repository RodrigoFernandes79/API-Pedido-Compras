package com.rodrigohf.apicompras.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.dtos.ClienteDTO;
import com.rodrigohf.apicompras.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;

	public Cliente listarClientePorId(Long id) {
		Optional<Cliente> obj = clienteRepo.findById(id);
		return obj
				.orElseThrow(()-> new RuntimeException("Objeto ID "+ id + " não Encontrado!!!"));
	}
	
	public Cliente inserirCliente(Cliente Cliente) {

		Cliente obj = clienteRepo.save(Cliente);

		return obj;
	}

	public Cliente atualizarCliente(Long id, Cliente Cliente) {
		return clienteRepo.findById(id).map(obj -> {
			obj.getId();
			obj.setNome(Cliente.getNome());
			obj.setEmail(Cliente.getEmail());

			
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
					"Objeto " + id + " Não pode ser apagado pois tem entidades associadas");
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
		return new Cliente(ClienteDTO.getId(), ClienteDTO.getNome(), ClienteDTO.getEmail(), null, null);
	}

	
	}


package com.rodrigohf.apicompras.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Categoria;
import com.rodrigohf.apicompras.domain.Cidade;
import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.domain.Endereco;
import com.rodrigohf.apicompras.domain.Estado;
import com.rodrigohf.apicompras.domain.ItemPedido;
import com.rodrigohf.apicompras.domain.Pagamento;
import com.rodrigohf.apicompras.domain.PagamentoComBoleto;
import com.rodrigohf.apicompras.domain.PagamentoComCartao;
import com.rodrigohf.apicompras.domain.Pedido;
import com.rodrigohf.apicompras.domain.Produto;
import com.rodrigohf.apicompras.domain.enums.EstadoPagamento;
import com.rodrigohf.apicompras.domain.enums.PerfilClientes;
import com.rodrigohf.apicompras.domain.enums.TipoCliente;
import com.rodrigohf.apicompras.repositories.CategoriaRepository;
import com.rodrigohf.apicompras.repositories.CidadeRepository;
import com.rodrigohf.apicompras.repositories.ClienteRepository;
import com.rodrigohf.apicompras.repositories.EnderecoRepository;
import com.rodrigohf.apicompras.repositories.EstadoRepository;
import com.rodrigohf.apicompras.repositories.ItemPedidoRepository;
import com.rodrigohf.apicompras.repositories.PagamentoRepository;
import com.rodrigohf.apicompras.repositories.PedidoRepository;
import com.rodrigohf.apicompras.repositories.ProdutoRepository;

//Classe de serviço criada para instanciar os dados no banco de dados H2
@Service
public class DataBaseService {

	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private CidadeRepository cidRepo;
	@Autowired
	private EstadoRepository estRepo;
	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository endRepo;
	@Autowired
	private PedidoRepository pedRepo;
	@Autowired
	private PagamentoRepository pagtoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void intanciarDataBaseTestProfile() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Casa e Decoração");
		Categoria cat4 = new Categoria(null, "Jogos e Eletrônicos");
		Categoria cat5 = new Categoria(null, "Perfumaria");
		Categoria cat6 = new Categoria(null, "Ferramentas");
		Categoria cat7 = new Categoria(null, "Carros, Motos e Outros");
		Categoria cat8 = new Categoria(null, "Imóveis");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colchão", 200.00);
		Produto p7 = new Produto(null, "Tv HD 27 polegadas", 1200.00);
		Produto p8 = new Produto(null, "Furadeira", 800.00);
		Produto p9 = new Produto(null, "Perfume Pacco Rabbane", 200.00);
		Produto p10 = new Produto(null, "Corsa Sedan 2011", 22300.00);
		Produto p11 = new Produto(null, "Apartamento Decorado 3 quartos", 320000.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p7));
		cat5.getProdutos().addAll(Arrays.asList(p9));
		cat6.getProdutos().addAll(Arrays.asList(p8));
		cat7.getProdutos().addAll(Arrays.asList(p10));
		cat8.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat6));
		p9.getCategorias().addAll(Arrays.asList(cat5));
		p10.getCategorias().addAll(Arrays.asList(cat7));
		p11.getCategorias().addAll(Arrays.asList(cat8));

		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estRepo.saveAll(Arrays.asList(est1, est2));
		cidRepo.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "rodrigohf79@hotmail.com", "36378912377",
				TipoCliente.PESSOAFISICA,passwordEncoder.encode("batata123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Cliente cli2 = new Cliente(null, "Roberto da Silva", "restaurantesabordolar@hotmail.com", "36378912377",
				TipoCliente.PESSOAJURIDICA,passwordEncoder.encode("12308"));
		cli2.getTelefones().addAll(Arrays.asList("36634522", "993659998"));
		cli2.addPerfil(PerfilClientes.ADMIN);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua da Palma", "68", null, "Centro", "6333515", cli2, c3);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		cliRepo.saveAll(Arrays.asList(cli1,cli2));
		endRepo.saveAll(Arrays.asList(e1, e2,e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), null, cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), null, cli1, e2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagtoRepo.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
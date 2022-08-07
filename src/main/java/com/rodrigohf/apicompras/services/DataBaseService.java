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
		
		Produto p12 = new Produto(null, "Pen Drive", 18.00);
		Produto p13 = new Produto(null, "Cabo Usb", 22.00);
		Produto p14 = new Produto(null, "Notebook", 3.000);
		Produto p15 = new Produto(null, "Fone de ouvido", 80.00);
		Produto p16 = new Produto(null, "Carregador para Notebook", 120.00);
		Produto p17 = new Produto(null, "Processador Intel", 830.00);
		Produto p18 = new Produto(null, "Capa para Computador", 223.00);
		Produto p19 = new Produto(null, "Computador Celeron 5giga", 4325.00);
		Produto p20 = new Produto(null, "HD externo SSD", 352.00);
		Produto p21 = new Produto(null, "Cabo HDMI", 56.00);
		Produto p22 = new Produto(null, "Notebook Lenovo", 3500.00);
		Produto p23 = new Produto(null, "Notebook Samsung", 4020.00);
		Produto p24 = new Produto(null, "Ipad 60", 5200.00);
		Produto p25 = new Produto(null, "Carregador para Ipad", 210.00);
		Produto p26 = new Produto(null, "Tablet Samsung", 3800.00);
		Produto p27 = new Produto(null, "Teclados", 180.00);
		Produto p28 = new Produto(null, "Caixa de Som", 120.00);
		Produto p29 = new Produto(null, "Mouse pads", 21.00);
		Produto p30 = new Produto(null, "Cartão de Memória", 223.00);
		Produto p31 = new Produto(null, "PC Intel", 1.250);
		Produto p32 = new Produto(null, "Computador Pentium 8° Geração", 2198.00);
		Produto p33 = new Produto(null, "Mini Conversor de Vídeo", 59.00);
		Produto p34 = new Produto(null, "Conector Transparente", 38.00);
		Produto p35 = new Produto(null, "Emenda Cabo de Rede", 6.00);
		Produto p36 = new Produto(null, "Roteador Intelbras", 338.00);
		Produto p37 = new Produto(null, "Connectv Digital", 138.00);
		Produto p38 = new Produto(null, "Receptor de TV Digital", 155.00);
		Produto p39 = new Produto(null, "Placa de Vídeo",145.00);
		Produto p40 = new Produto(null, "Kit Upgrade 10° Geração", 2112.00);
		Produto p41 = new Produto(null, "Kit Upgrade 8° Geração", 1698.00);
		Produto p42 = new Produto(null, "Placa Mãe Kronus", 648.00);
		Produto p43 = new Produto(null, "Placa Mãe Ipx",624.00);
		Produto p44 = new Produto(null, "Cpu Gamer Rizer", 2332.00);
		Produto p45 = new Produto(null, "Webcan Multilaser", 312.00);
		Produto p46 = new Produto(null, "Conversor Usb", 43.00);
		Produto p47 = new Produto(null, "Extensão Portas Usb", 32.00);
		Produto p48 = new Produto(null, "Usb Virtual Channel Adapter", 18.00);
		Produto p49 = new Produto(null, "Emenda HDMI", 16.00);
		Produto p50 = new Produto(null, "Conversor HDMI", 28.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		p12.getCategorias().addAll(Arrays.asList(cat1));
		p13.getCategorias().addAll(Arrays.asList(cat1));
		p14.getCategorias().addAll(Arrays.asList(cat1));
		p15.getCategorias().addAll(Arrays.asList(cat1));
		p16.getCategorias().addAll(Arrays.asList(cat1));
		p17.getCategorias().addAll(Arrays.asList(cat1));
		p18.getCategorias().addAll(Arrays.asList(cat1));
		p19.getCategorias().addAll(Arrays.asList(cat1));
		p20.getCategorias().addAll(Arrays.asList(cat1));
		p21.getCategorias().addAll(Arrays.asList(cat1));
		p22.getCategorias().addAll(Arrays.asList(cat1));
		p23.getCategorias().addAll(Arrays.asList(cat1));
		p24.getCategorias().addAll(Arrays.asList(cat1));
		p25.getCategorias().addAll(Arrays.asList(cat1));
		p26.getCategorias().addAll(Arrays.asList(cat1));
		p27.getCategorias().addAll(Arrays.asList(cat1));
		p28.getCategorias().addAll(Arrays.asList(cat1));
		p29.getCategorias().addAll(Arrays.asList(cat1));
		p30.getCategorias().addAll(Arrays.asList(cat1));
		p31.getCategorias().addAll(Arrays.asList(cat1));
		p32.getCategorias().addAll(Arrays.asList(cat1));
		p33.getCategorias().addAll(Arrays.asList(cat1));
		p34.getCategorias().addAll(Arrays.asList(cat1));
		p35.getCategorias().addAll(Arrays.asList(cat1));
		p36.getCategorias().addAll(Arrays.asList(cat1));
		p37.getCategorias().addAll(Arrays.asList(cat1));
		p38.getCategorias().addAll(Arrays.asList(cat1));
		p39.getCategorias().addAll(Arrays.asList(cat1));
		p40.getCategorias().addAll(Arrays.asList(cat1));
		p41.getCategorias().addAll(Arrays.asList(cat1));
		p42.getCategorias().addAll(Arrays.asList(cat1));
		p43.getCategorias().addAll(Arrays.asList(cat1));
		p44.getCategorias().addAll(Arrays.asList(cat1));
		p45.getCategorias().addAll(Arrays.asList(cat1));
		p46.getCategorias().addAll(Arrays.asList(cat1));
		p47.getCategorias().addAll(Arrays.asList(cat1));
		p48.getCategorias().addAll(Arrays.asList(cat1));
		p49.getCategorias().addAll(Arrays.asList(cat1));
		p50.getCategorias().addAll(Arrays.asList(cat1));
	

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
		
		prodRepo.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estRepo.saveAll(Arrays.asList(est1, est2));
		cidRepo.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "rodrigohf79@hotmail.com", "02704479461",
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
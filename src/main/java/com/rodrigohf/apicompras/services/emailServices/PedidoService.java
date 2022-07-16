package com.rodrigohf.apicompras.services.emailServices;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.ItemPedido;
import com.rodrigohf.apicompras.domain.PagamentoComBoleto;
import com.rodrigohf.apicompras.domain.Pedido;
import com.rodrigohf.apicompras.domain.enums.EstadoPagamento;
import com.rodrigohf.apicompras.repositories.ItemPedidoRepository;
import com.rodrigohf.apicompras.repositories.PagamentoRepository;
import com.rodrigohf.apicompras.repositories.PedidoRepository;
import com.rodrigohf.apicompras.services.BoletoService;
import com.rodrigohf.apicompras.services.ClienteService;
import com.rodrigohf.apicompras.services.ProdutoService;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedRepo;
	@Autowired 
	private ClienteService clienteService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired 
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private EmailService emailService;

	public Pedido listarPedidoPorId(Long id) {
		Optional<Pedido> obj = pedRepo.findById(id);
		return obj
				.orElseThrow(()-> new RuntimeException("Objeto ID "+ id + " não Encontrado!!!"));
	}

	@Transactional
	public Pedido inserirCategoria(@Valid Pedido pedido) {
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.listarClientePorId(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = pedRepo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.listarProdutoPorId(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
			
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.emailDeConfirmaçãoDoPedido(pedido);
		
		return pedido;
		
	}

}

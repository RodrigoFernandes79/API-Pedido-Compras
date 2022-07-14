package com.rodrigohf.apicompras.services;

import java.util.Date;
import java.util.Optional;

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

	public Pedido listarPedidoPorId(Long id) {
		Optional<Pedido> obj = pedRepo.findById(id);
		return obj
				.orElseThrow(()-> new RuntimeException("Objeto ID "+ id + " não Encontrado!!!"));
	}

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
		
		//automaticamente ele mostra o toString de pedido no log do console(usado para configuraçao de serviço de email)
		System.out.println(pedido);
		return pedido;
		
	}

}

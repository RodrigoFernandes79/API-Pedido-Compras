package com.rodrigohf.apicompras.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Pedido;
import com.rodrigohf.apicompras.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedRepo;

	public Pedido listarPedidoPorId(Long id) {
		Optional<Pedido> obj = pedRepo.findById(id);
		return obj
				.orElseThrow(()-> new RuntimeException("Objeto ID "+ id + " não Encontrado!!!"));
	}

}

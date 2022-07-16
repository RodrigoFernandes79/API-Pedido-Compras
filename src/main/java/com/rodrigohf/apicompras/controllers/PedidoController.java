package com.rodrigohf.apicompras.controllers;



import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigohf.apicompras.domain.Pedido;
import com.rodrigohf.apicompras.services.emailServices.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> listarPedidoPorId(@PathVariable Long id){
		
		Pedido obj = pedidoService.listarPedidoPorId(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	@PostMapping
	public ResponseEntity<Pedido> inserirCategoria(@Valid @RequestBody Pedido pedido){
		
		Pedido obj = pedidoService.inserirCategoria(pedido);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}

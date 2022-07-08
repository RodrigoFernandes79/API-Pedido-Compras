package com.rodrigohf.apicompras.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.apicompras.domain.Pedido;
import com.rodrigohf.apicompras.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService catService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> listarPedidoPorId(@PathVariable Long id){
		
		Pedido obj = catService.listarPedidoPorId(id);
		
		return ResponseEntity.ok().body(obj);
		
	}

}

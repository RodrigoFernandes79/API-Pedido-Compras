package com.rodrigohf.apicompras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id) {

		Cliente obj = clienteService.listarClientePorId(id);

		return ResponseEntity.ok().body(obj);

	}

}

package com.rodrigohf.apicompras.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.dtos.ClienteDTO;
import com.rodrigohf.apicompras.dtos.ClienteNewDTO;
import com.rodrigohf.apicompras.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id)  {

		Cliente obj = clienteService.listarClientePorId(id);

		return ResponseEntity.ok().body(obj);

	}
	
	@PostMapping
	public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody ClienteNewDTO clienteNewDTO){
		Cliente objDTO = clienteService.fromDTO(clienteNewDTO);
		Cliente obj = clienteService.inserirCliente(objDTO);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
		Cliente objDTO = clienteService.fromDTO(clienteDTO);
		Cliente obj = clienteService.atualizarCliente(id,objDTO);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //somente admins podem acessar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletarClientePorId(@PathVariable Long id) {
		clienteService.deletarClientePorId(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //somente admins podem acessar
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarCliente(){
		
		List<ClienteDTO> objListDTO = clienteService.listarCliente();
		
		
		return ResponseEntity.ok().body(objListDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //somente admins podem acessar
	//Paginação com parâmetros opcionais na requisição
	@GetMapping("/pages")
	public ResponseEntity<Page<ClienteDTO>> listarPaginacao(
			@RequestParam(value="page",defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value="direction",defaultValue = "ASC") String direction){
		
		Page<ClienteDTO> objListDTO = clienteService.listarPaginacao(page,linesPerPage,orderBy,direction);
		
		
		return ResponseEntity.ok().body(objListDTO);
}
}

package com.rodrigohf.apicompras.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.apicompras.dtos.CidadeDTO;
import com.rodrigohf.apicompras.dtos.EstadoDTO;
import com.rodrigohf.apicompras.services.CidadeService;
import com.rodrigohf.apicompras.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService service;
	@Autowired
	private CidadeService cidadeService;
	
	//retornando lista de estados:
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> listarEstados(){
				List<EstadoDTO> objDTO = service.listarEstados();
				return ResponseEntity.ok().body(objDTO);
			}
	
	//retornando lista de cidades passando o id do estado como argumento:
	@GetMapping("/{estado_id}/cidades")
	public ResponseEntity<List<CidadeDTO>> encontrarCidades(@PathVariable Long estado_id){
				List<CidadeDTO> objDTO = cidadeService.encontrarCidades(estado_id);
				return ResponseEntity.ok().body(objDTO);
			}
	
}


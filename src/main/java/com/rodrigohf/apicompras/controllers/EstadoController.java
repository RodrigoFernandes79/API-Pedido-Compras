package com.rodrigohf.apicompras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<EstadoDTO>> listarEstadosPorDescricaoOrdenada(
			@RequestParam(value="page",defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value="direction",defaultValue = "ASC") String direction){
				Page<EstadoDTO> objDTO = service.listarEstadosPorDescricaoOrdenada(page, linesPerPage, orderBy, direction);
			
				return ResponseEntity.ok().body(objDTO);
			}
	//retornando lista de cidades passando o id do estado como argumento:
	@GetMapping("/{estado_id}/cidades")
	public ResponseEntity<Page<CidadeDTO>> encontrarCidades(
			@PathVariable Long estado_id,
			@RequestParam(value="page",defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value="direction",defaultValue = "ASC") String direction){
				Page<CidadeDTO> objDTO = cidadeService.encontrarCidades(estado_id,
																		page,
																		linesPerPage,	
																		orderBy,
																		direction);
				return ResponseEntity.ok().body(objDTO);
			}
		
}


package com.rodrigohf.apicompras.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.apicompras.domain.Categoria;
import com.rodrigohf.apicompras.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService catService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> listarCategoriaPorId(@PathVariable Long id){
		
		Categoria obj = catService.listarCategoriaPorId(id);
		
		return ResponseEntity.ok().body(obj);
		
	}

}

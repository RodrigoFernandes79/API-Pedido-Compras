package com.rodrigohf.apicompras.controllers;



import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigohf.apicompras.domain.Categoria;
import com.rodrigohf.apicompras.dtos.CategoriaDTO;
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
	
	@PostMapping
	public ResponseEntity<Categoria> inserirCategoria(@RequestBody Categoria categoria){
		
		Categoria obj = catService.inserirCategoria(categoria);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id,@RequestBody Categoria categoria){
		
		Categoria obj = catService.atualizarCategoria(id,categoria);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletarCategoriaPorId(@PathVariable Long id) {
		catService.deletarCategoriaPorId(id);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listarCategoria(){
		
		List<CategoriaDTO> objListDTO = catService.listarCategoria();
		
		
		return ResponseEntity.ok().body(objListDTO);
	}

	
}


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

import com.rodrigohf.apicompras.domain.Categoria;
import com.rodrigohf.apicompras.dtos.CategoriaDTO;
import com.rodrigohf.apicompras.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService catService;
	
	@GetMapping("/pages/{id}")
	public ResponseEntity<Page<Categoria>> listarCategoriaPorId(@PathVariable Long id,@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction){
		
		Page<Categoria> obj = catService.listarCategoriaPorId(id,page,linesPerPage,orderBy,direction);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //somente admins podem acessar
	@PostMapping
	public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
		Categoria objDTO = catService.fromDTO(categoriaDTO);
		Categoria obj = catService.inserirCategoria(objDTO);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')") //somente admins podem acessar
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO){
		Categoria objDTO = catService.fromDTO(categoriaDTO);
		Categoria obj = catService.atualizarCategoria(id,objDTO);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //somente admins podem acessar
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
	//Paginação com parâmetros opcionais na requisição
	@GetMapping("/pages")
	public ResponseEntity<Page<CategoriaDTO>> listarPaginacao(
			@RequestParam(value="page",defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value="direction",defaultValue = "ASC") String direction){
		
		Page<CategoriaDTO> objListDTO = catService.listarPaginacao(page,linesPerPage,orderBy,direction);
		
		
		return ResponseEntity.ok().body(objListDTO);
}
	
}

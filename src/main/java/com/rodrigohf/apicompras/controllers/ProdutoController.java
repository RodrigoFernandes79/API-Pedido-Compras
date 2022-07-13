package com.rodrigohf.apicompras.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.apicompras.domain.Produto;
import com.rodrigohf.apicompras.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> listarProdutoPorId(@PathVariable Long id){
		
		Produto obj = produtoService.listarProdutoPorId(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping //localhost/8080/produtos/?nome="nome"
	public ResponseEntity<Page<Produto>> listarProdutoPorDescricaoPaginacao(
			@RequestParam(value="nome",required=false) String nome,
			@RequestParam(value="page",defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value="direction",defaultValue = "ASC") String direction){
		
		Page<Produto> objList = produtoService.listarProdutoPorDescricaoPaginacao(nome, page, linesPerPage, orderBy, direction);
		
		
		return ResponseEntity.ok().body(objList);
}
}

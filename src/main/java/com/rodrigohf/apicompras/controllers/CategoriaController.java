package com.rodrigohf.apicompras.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.apicompras.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1L, "Informática");
		Categoria cat2 = new Categoria(2L, "Escritório");
		
		List<Categoria> listar = new ArrayList<>();
		listar.add(cat1);
		listar.add(cat2);
		return listar;
	}

}

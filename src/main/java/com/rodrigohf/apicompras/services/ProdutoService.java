package com.rodrigohf.apicompras.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Produto;
import com.rodrigohf.apicompras.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepo;

	public Produto listarProdutoPorId(Long id) {
		Optional<Produto> obj = produtoRepo.findById(id);
		return obj
				.orElseThrow(()-> new RuntimeException("Objeto ID "+ id + " não Encontrado!!!"));
	}
	
	public Page<Produto> listarProdutoPorDescricaoPaginacao(String nome,Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of( page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<Produto> objList = produtoRepo.listarProdutoPorDescricao(nome, pageRequest);
		

		return objList;
	}

}

package com.rodrigohf.apicompras.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigohf.apicompras.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Transactional(readOnly=true)
	@Query(value="SELECT p FROM PRODUTO p WHERE p.NOME LIKE %:nome%" , nativeQuery = true) //ilike ignora case sensitive
	Page<Produto> listarProdutoPorNome(@Param(value="nome")String nome, PageRequest pageRequest);
	
	
}





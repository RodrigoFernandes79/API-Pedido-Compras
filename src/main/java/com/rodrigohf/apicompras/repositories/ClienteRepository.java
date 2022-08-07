package com.rodrigohf.apicompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigohf.apicompras.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Transactional(readOnly=true)
	public Cliente findByEmail(String email);
	
	@Transactional(readOnly=true)
	@Query(value = "SELECT * FROM cliente WHERE cpf_ou_cnpj = :cpf_ou_cnpj", nativeQuery = true)
	public Cliente findByCpfOuCnpj(@Param(value = "cpf_ou_cnpj")String cpf_ou_cnpj);
	

}

package com.rodrigohf.apicompras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigohf.apicompras.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM cidade c WHERE u.estado_id = :estado_id", nativeQuery = true)
	List<Cidade> findCidades(@Param(value = "estado_id") Long estado_id);

	
}

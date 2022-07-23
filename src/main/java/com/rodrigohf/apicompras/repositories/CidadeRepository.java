package com.rodrigohf.apicompras.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigohf.apicompras.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM cidade WHERE estado_id = :estado_id", nativeQuery = true)
	Page<Cidade> findCidades(@Param(value = "estado_id") Long estado_id, PageRequest pageRequest);
}

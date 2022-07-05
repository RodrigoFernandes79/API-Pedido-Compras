package com.rodrigohf.apicompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigohf.apicompras.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}

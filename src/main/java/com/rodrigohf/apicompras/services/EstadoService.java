package com.rodrigohf.apicompras.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Estado;
import com.rodrigohf.apicompras.dtos.EstadoDTO;
import com.rodrigohf.apicompras.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public List<EstadoDTO> listarEstados() {

		List<Estado> objList = repo.findAllByOrderByNome();
		List<EstadoDTO> objListDTO = objList.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());

		return objListDTO;
	}

}

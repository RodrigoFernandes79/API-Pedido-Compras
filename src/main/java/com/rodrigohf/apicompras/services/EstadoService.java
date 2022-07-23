package com.rodrigohf.apicompras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Estado;
import com.rodrigohf.apicompras.dtos.EstadoDTO;
import com.rodrigohf.apicompras.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Page<EstadoDTO> listarEstadosPorDescricaoOrdenada(Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<Estado> objList = repo.findAllByOrderByNome(pageRequest);
		Page<EstadoDTO> objListDTO = objList.map(obj -> new EstadoDTO(obj));

		return objListDTO;
	}

}

package com.rodrigohf.apicompras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Cidade;
import com.rodrigohf.apicompras.dtos.CidadeDTO;
import com.rodrigohf.apicompras.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public Page<CidadeDTO> encontrarCidades(Long estado_id, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<Cidade> objList = cidadeRepository.findCidades(estado_id, pageRequest);
		Page<CidadeDTO> objListDTO = objList.map(obj -> new CidadeDTO(obj));

		return objListDTO;

	}
}
package com.rodrigohf.apicompras.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Cidade;
import com.rodrigohf.apicompras.dtos.CidadeDTO;

import com.rodrigohf.apicompras.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<CidadeDTO> encontrarCidades(Long estado_id) {

		List<Cidade> objList = cidadeRepository.findCidades(estado_id);
		List<CidadeDTO> objListDTO = objList.stream().map(obj ->new CidadeDTO(obj)).collect(Collectors.toList());
		return objListDTO;

	}
}
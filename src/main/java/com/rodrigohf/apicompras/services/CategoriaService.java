package com.rodrigohf.apicompras.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Categoria;
import com.rodrigohf.apicompras.dtos.CategoriaDTO;
import com.rodrigohf.apicompras.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepo;

	public Categoria listarCategoriaPorId(Long id) {
		Optional<Categoria> obj = catRepo.findById(id);
		return obj.orElseThrow(() -> new RuntimeException("Objeto ID " + id + " não Encontrado!!!"));
	}

	public Categoria inserirCategoria(Categoria categoria) {

		Categoria obj = catRepo.save(categoria);

		return obj;
	}

	public Categoria atualizarCategoria(Long id, Categoria categoria) {
		return catRepo.findById(id).map(obj -> {
			obj.getId();
			obj.setNome(categoria.getNome());

			Categoria cat = catRepo.save(obj);
			return cat;
		}).orElseThrow(() -> new RuntimeException("Objeto ID " + id + " não Encontrado!!!"));

	}

	public void deletarCategoriaPorId(Long id) {
		
		try {
			catRepo.findById(id).orElseThrow(() -> new RuntimeException("Objeto ID " + id + " não Encontrado!!!"));
			catRepo.deleteById(id);
		
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException(
					"Objeto " + id + " Não pode ser apagado pois tem produtos associados");
		}
	}

	public List<CategoriaDTO> listarCategoria() {

		List<Categoria> objList = catRepo.findAll();
		
		List<CategoriaDTO> objListDTO = objList.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return objListDTO;
		
	}
}
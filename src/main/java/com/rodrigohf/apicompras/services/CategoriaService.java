package com.rodrigohf.apicompras.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Categoria;
import com.rodrigohf.apicompras.dtos.CategoriaDTO;
import com.rodrigohf.apicompras.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepo;

	public Page<Categoria> listarCategoriaPorId(Long id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		Categoria categoria = catRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Objeto ID " + id + " não Encontrado!!!"));

				
				
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
			 
			Page<Categoria>  cat = catRepo.findById(categoria.getId(), pageRequest);
				
			return cat;
		
		
		  
	}

	public Categoria inserirCategoria( Categoria categoria) {
       
		
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
	//Paginação com parâmetros opcionais na requisição(page= n° de paginas, linesPerPage= qtdade de linhas por cada pág,
	//orderBy = ordenar por qual atributo(id ou nome), direction = ascendente ou descendente) 
	public Page<CategoriaDTO> listarPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<Categoria> objList = catRepo.findAll(pageRequest);
		Page<CategoriaDTO> objListDTO = objList.map(obj-> new CategoriaDTO(obj));
		
		return objListDTO;
	}
	
	//método auxiliar para instanciar Categoria a partir do objeto CategoriaDTO 
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
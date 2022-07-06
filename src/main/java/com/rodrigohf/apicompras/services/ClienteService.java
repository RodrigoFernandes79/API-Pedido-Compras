package com.rodrigohf.apicompras.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;

	public Cliente listarClientePorId(Long id) {
		Optional<Cliente> obj = clienteRepo.findById(id);
		return obj
				.orElseThrow(()-> new RuntimeException("Objeto ID "+ id + " não Encontrado!!!"));
	}

	

}

package com.rodrigohf.apicompras;

import java.sql.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigohf.apicompras.domain.Categoria;
import com.rodrigohf.apicompras.repositories.CategoriaRepository;

@SpringBootApplication
public class ApicomprasApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository catRepo;

	public static void main(String[] args) {
		SpringApplication.run(ApicomprasApplication.class, args);
	
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		
	}

}

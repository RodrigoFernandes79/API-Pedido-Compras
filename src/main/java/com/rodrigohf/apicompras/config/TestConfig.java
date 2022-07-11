package com.rodrigohf.apicompras.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigohf.apicompras.services.DataBaseService;

//classe para inserir os dados CommandLineRunner e instanciar os dados apenas quando rodar no banco H2
@Configuration
@Profile("test") // indica que essa classe de configuração pertence ao profile de test
public class TestConfig {

	@Autowired
	private DataBaseService dbService;

	@Bean
	public boolean intanciarDataBaseTestProfile() throws ParseException {

		dbService.intanciarDataBaseTestProfile();

		return true;
	}
}

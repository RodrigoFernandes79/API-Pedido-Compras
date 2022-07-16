package com.rodrigohf.apicompras.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigohf.apicompras.services.DataBaseService;
import com.rodrigohf.apicompras.services.SmtpEmailService;
import com.rodrigohf.apicompras.services.emailServices.EmailService;

//classe para inserir os dados CommandLineRunner e instanciar os dados apenas quando rodar no banco MySql
@Configuration
@Profile("dev") // indica que essa classe de configuração pertence ao profile de dev
public class DevConfig {

	@Autowired
	private DataBaseService dbService;
	
	

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean intanciarDataBaseTestProfile() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		dbService.intanciarDataBaseTestProfile();

		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	
}

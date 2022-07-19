package com.rodrigohf.apicompras.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.repositories.ClienteRepository;
import com.rodrigohf.apicompras.services.emailServices.EmailService;
//Classe criada para regra de negocio de esqueci a senha:
@Service
public class AuthService {
	
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = cliRepository.findByEmail(email);
		if(cliente==null) {
			throw new RuntimeException("Email  não encontrado!");
		
		}
		
		String newPass = newPassword();
		cliente.setSenha(passwordEncoder.encode(newPass)); //inserindo nova senha
		
		cliRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente,newPass);
		
	}
    //método auxiliar que vai gerar uma senha aleatória
	private String newPassword() {
		char[] vet = new char[10]; //criando uma senha aleatoria de 10 caracteres
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
	private char randomChar() {
		Random  random = new Random(); // instanciando classe do java pra gerar caracteres randomicos
		
		int opt = random.nextInt(3); //objeto que randomiza de 0 a 2:
		if(opt==0) { //se o número randomizado for 0, gera  dígito: www.unicode-table.com -> onde ficam as tabelas
			return (char) (random.nextInt(10)+48); 
		}else if(opt==1) {//se o número randomizado for 0, gera letra maiuscula
			return (char) (random.nextInt(26)+65);
		}else { //se for 2 , gera letra minuscula
			return (char) (random.nextInt(26)+97);
		}
		
	}

}

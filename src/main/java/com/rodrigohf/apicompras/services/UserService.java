package com.rodrigohf.apicompras.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.rodrigohf.apicompras.security.UserSpringSecurity;

//Classe que implementa um método que retorna o usuário logado

public class UserService {

	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}
}

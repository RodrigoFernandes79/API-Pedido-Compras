package com.rodrigohf.apicompras.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.apicompras.dtos.EmailDTO;
import com.rodrigohf.apicompras.security.JWTUtil;
import com.rodrigohf.apicompras.security.UserSpringSecurity;
import com.rodrigohf.apicompras.services.AuthService;
import com.rodrigohf.apicompras.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UserService.authenticated();
		String token = jwtUtil.StringGenerateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot( @Valid @RequestBody EmailDTO emailDTO) {

		authService.sendNewPassword(emailDTO.getEmail());

		return ResponseEntity.noContent().build();
	}

}

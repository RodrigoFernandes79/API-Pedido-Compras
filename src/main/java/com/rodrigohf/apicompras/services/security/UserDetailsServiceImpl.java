package com.rodrigohf.apicompras.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.repositories.ClienteRepository;
import com.rodrigohf.apicompras.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepo;

	//método para buscar o UserSpringSecuriy pelo email:
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = clienteRepo.findByEmail(email);
		if(email==null) {
			
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSpringSecurity(cli.getId(),cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}

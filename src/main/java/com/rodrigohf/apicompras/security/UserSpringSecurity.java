package com.rodrigohf.apicompras.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rodrigohf.apicompras.domain.enums.PerfilClientes;

public class UserSpringSecurity implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private  Collection<? extends GrantedAuthority> autorizacoes;
	
	

	public UserSpringSecurity() {
		
	}
	

	public UserSpringSecurity(Long id, String email, String senha,
			Set<PerfilClientes> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.autorizacoes = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		
		return senha;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

//(Spring Security)método para saber se o Usuário tem perfil de admin:
	public boolean hasRole(PerfilClientes admin) {
		
		return getAuthorities().contains(new SimpleGrantedAuthority(admin.getDescricao()));
	}


	


	

}

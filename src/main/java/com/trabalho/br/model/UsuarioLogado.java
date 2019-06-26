package com.trabalho.br.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UsuarioLogado extends User{
	private String userLogadoNome;
	private Long userLogadoId;

	public UsuarioLogado(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public String getLoginUsuario() {
		UsuarioLogado user = (UsuarioLogado) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return user.getUsername().toString();
	}

	public String getUserLogadoNome() {
		return userLogadoNome;
	}

	public void setUserLogadoNome(String userLogadoNome) {
		this.userLogadoNome = userLogadoNome;
	}

	public Long getUserLogadoId() {
		return userLogadoId;
	}

	public void setUserLogadoId(Long userLogadoId) {
		this.userLogadoId = userLogadoId;
	}
	
	
	
	
	

}

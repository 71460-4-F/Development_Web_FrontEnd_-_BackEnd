package com.trabalho.br.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	
	@Id
	private String nomeRole;
	
	@ManyToMany(mappedBy = "roles")
	private List<Cliente> clientes;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nomeRole;
	}

	public String getPapel() {
		return nomeRole;
	}

	public void setPapel(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	

}

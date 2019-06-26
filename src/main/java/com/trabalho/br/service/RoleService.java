package com.trabalho.br.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.br.model.Role;
import com.trabalho.br.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void salvarRoleDinamicamente() {
		Role role = new Role();
        role.setPapel("ROLE_USER");
        roleRepository.save(role);
	}
	
	public void salvarRoleDinamicamente(Role role) {
        roleRepository.save(role);
	}
	
	
	
	public Role buscarRule(String role) {
		return roleRepository.getOne(role);
	}

}

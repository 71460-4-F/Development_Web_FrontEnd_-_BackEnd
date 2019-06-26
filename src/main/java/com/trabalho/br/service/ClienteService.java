package com.trabalho.br.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabalho.br.model.Cliente;
import com.trabalho.br.model.Role;
import com.trabalho.br.repository.ClienteRepository;
import com.trabalho.br.repository.RoleRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    
	@Autowired
	private RoleRepository roleRepository;

    public void save(Cliente cliente) {
    	cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        Role role = new Role();
        role.setPapel("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        cliente.setRoles(roles);
    	
        clienteRepository.save(cliente);
    }

    public List<Cliente> listClientes() {
        return clienteRepository.findAll();
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente serchById(Long id) {
        return clienteRepository.getOne(id);
    }
    
	public Cliente buscarPorId(Long id) {
		return clienteRepository.getOne(id);
	}
	
    
	public Cliente buscarEmail(String email) {
		return clienteRepository.findByEmail(email);
	}
	
	public Cliente clienteLogado() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		return clienteRepository.findByEmail(user.getUsername());
	}

	
	
	public void setarGerente() {
		Role role = new Role();
		role.setPapel("ROLE_GERENTE");
		Cliente gerente = new Cliente(null, Arrays.asList(role), "Gerente", "000.000.000-00",
				null, new Date(1989, 06, 29), "gerente@mail.com",
				"$2a$10$lWEmW3k9ekVDMtyOUNiQ2e6SWrQWik/laqVSPUCyMNW4bjscDOYEa", null);
		clienteRepository.save(gerente);
	}
	
//	public void setarAdmin() {
//		Role role = new Role();
//		role.setPapel("ROLE_ADMIN");
//		Cliente admin = new Cliente(null, Arrays.asList(role), "Admin", "000.000.000-00",
//				null, new Date(1989, 06, 29), "admin@mail.com", 
//				"$2a$10$E4kOBGvSa9dkJu7E2YESmOjtgDF43KbYbLtS4R7FWdWG/2S9B4Equ", null);
//		clienteRepository.save(admin);
//	}
	
	public boolean logado() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		if (clienteRepository.findByEmail(user.getUsername()).isEnabled()) {
		return true;
		}
		
		else 
			return false;
	}

}

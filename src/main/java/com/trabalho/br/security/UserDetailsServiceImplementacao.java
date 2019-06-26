package com.trabalho.br.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.trabalho.br.model.Cliente;
import com.trabalho.br.repository.ClienteRepository;

@Repository
@Transactional
public class UserDetailsServiceImplementacao implements UserDetailsService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			Cliente cliente = clienteRepository.findByEmail(email);
			
			if(cliente == null) {
				throw new UsernameNotFoundException("Cliente não encontrada");
			}
		
		return new User(cliente.getUsername(),cliente.getPassword(),true,true,true,true,cliente.getAuthorities());
	}
	
}

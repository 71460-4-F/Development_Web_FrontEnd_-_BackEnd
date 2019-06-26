package com.trabalho.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.br.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByEmail(String email);
}

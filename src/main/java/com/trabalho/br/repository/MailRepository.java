package com.trabalho.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.br.model.Cliente;

public interface MailRepository extends JpaRepository<Cliente, Long>{

}

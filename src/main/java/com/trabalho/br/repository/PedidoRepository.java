package com.trabalho.br.repository;

import com.trabalho.br.model.Cliente;
import com.trabalho.br.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

}

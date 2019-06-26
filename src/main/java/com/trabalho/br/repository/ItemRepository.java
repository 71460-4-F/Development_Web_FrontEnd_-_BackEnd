package com.trabalho.br.repository;

import com.trabalho.br.model.Item;
import com.trabalho.br.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByPedido(Pedido pedido);

}

package com.trabalho.br.service;

import com.trabalho.br.model.Cliente;
import com.trabalho.br.model.Pedido;
import com.trabalho.br.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public List<Pedido> listPedidos() {
        return pedidoRepository.findAll();
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido serchById(Long id) {
        return pedidoRepository.getOne(id);
    }

    public List<Pedido> findCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
}

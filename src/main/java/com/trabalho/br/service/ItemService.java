package com.trabalho.br.service;

import com.trabalho.br.model.Item;
import com.trabalho.br.model.Pedido;
import com.trabalho.br.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void saveAll(Iterable<Item> itens) {
        itemRepository.saveAll(itens);
    }

    public List<Item> listItens() {
        return itemRepository.findAll();
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Item serchById(Long id) {
        return itemRepository.getOne(id);
    }

    public List<Item> findItem(Pedido pedido) {
        return itemRepository.findByPedido(pedido);
    }

}

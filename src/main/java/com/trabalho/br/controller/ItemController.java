package com.trabalho.br.controller;

import com.trabalho.br.model.Item;
import com.trabalho.br.model.Pedido;
import com.trabalho.br.service.ItemService;
import com.trabalho.br.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping("/listar/{id}")
    public ModelAndView listar(@PathVariable Long id) {

        Pedido pedido = pedidoService.serchById(id);

        List<Item> listaItens = itemService.findItem(pedido);

        ModelAndView mv = new ModelAndView("/pedido/listar_itens");
        mv.addObject("listaItens", listaItens);
        return mv;

    }

}

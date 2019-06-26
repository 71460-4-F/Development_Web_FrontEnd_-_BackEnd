package com.trabalho.br.controller;

import com.trabalho.br.model.Item;
import com.trabalho.br.model.Pedido;
import com.trabalho.br.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CestaController {
	
    Pedido pedido = new Pedido();

    @Autowired
    PratoService pratoService;

    @RequestMapping(value = "pedido/selecionados")
    public ModelAndView selecionado() {
        ModelAndView mv = new ModelAndView("/pedido/selecionados");
        return mv;
    }

    @RequestMapping(value = "/pedido/adicionar/{id}")
    public ModelAndView adicionar(@PathVariable("id") Long id, HttpSession session) {
        Double total = 0.0;
        if (session.getAttribute("cesta") == null) {
            List<Item> cestaItem = new ArrayList<>();
            Item item = new Item();
            item.setPrato(pratoService.serchById(id));
            item.setQuantidade(1L);
            item.setPreco(pratoService.serchById(id).getPreco());
            cestaItem.add(item);
            total = item.getPreco();
            session.setAttribute("cesta", cestaItem);
            session.setAttribute("total", total);
        } else {
            List<Item> cestaItem = (List<Item>) session.getAttribute("cesta");
            int index = this.exists(id, cestaItem);
            if (index == -1) {
                Item item = new Item();
                item.setPrato(pratoService.serchById(id));
                item.setQuantidade(1L);
                item.setPreco(pratoService.serchById(id).getPreco());
                cestaItem.add(item);
            } else {
                cestaItem.get(index).increaseQuantidade();
            }
            for (Item i : cestaItem) {
                total += i.getPreco() * i.getQuantidade();
            }
            session.setAttribute("cesta", cestaItem);
            session.setAttribute("total", pedido.arredondaTotal(total));
        }

        ModelAndView mv = new ModelAndView("redirect:/pedido/selecionados");
        return mv;
    }

    @RequestMapping(value = "/pedido/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, HttpSession session) {
        Double total = 0.0;
        List<Item> cestaItem = (List<Item>) session.getAttribute("cesta");
        int index = this.exists(id, cestaItem);
        cestaItem.get(index).decreaseQuantidade();
        if (cestaItem.get(index).getQuantidade() == 0)
            cestaItem.remove(index);
        for (Item i : cestaItem) {
            total += i.getPreco() * i.getQuantidade();
        }
        session.setAttribute("cesta", cestaItem);
        session.setAttribute("total", pedido.arredondaTotal(total));
        ModelAndView mv = new ModelAndView("redirect:/pedido/selecionados");
        return mv;
    }

    private int exists(Long id, List<Item> cestaItem) {

        for (int i = 0; i < cestaItem.size(); i++) {
            if (cestaItem.get(i).getPrato().getIdPrato() == id)
                return i;
        }

        return -1;
    }

}

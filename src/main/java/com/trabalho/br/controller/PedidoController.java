package com.trabalho.br.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.br.model.Cliente;
import com.trabalho.br.model.Item;
import com.trabalho.br.model.Pedido;
import com.trabalho.br.service.ClienteService;
import com.trabalho.br.service.ItemService;
import com.trabalho.br.service.PedidoService;

@Controller
public class PedidoController {

	 @Autowired
	    private PedidoService pedidoService;

	    @Autowired
	    private ItemService itemService;

	    @Autowired
	    private ClienteService clienteService;

	    @RequestMapping("/pedido/salvar")
	    public ModelAndView salvar(HttpSession session) {

	        Iterable<Item> cesto = (Iterable<Item>) session.getAttribute("cesta");
	        if(IterableUtils.size(cesto) < 1)
	            return new ModelAndView("/pedido/selecionados");

	        Pedido pedido = new Pedido();

	        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        UserDetails user = (UserDetails) auth;

	        Cliente cliente = clienteService.buscarEmail(user.getUsername());

	        pedido.setCliente(cliente);
	        pedido.setTotal(0.0);
	        pedidoService.save(pedido);

	        Double total = 0.0;
	        for (Item item : cesto) {

	            item.setPedido(pedido);
	            total += item.getPreco() * item.getQuantidade();
	        }
	        

	        itemService.saveAll(cesto);
	        
	        pedido.setTotal(pedido.arredondaTotal(total));
	        pedido.setPendente(1);
	        pedidoService.save(pedido);


	        ModelAndView mv = new ModelAndView("redirect:/pedido/confirmado");
	        return mv;
	    }

	    @RequestMapping("/pedido/listar")
	    public ModelAndView listar() {

	        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        UserDetails user = (UserDetails) auth;

	        Cliente cliente = clienteService.buscarEmail(user.getUsername());

	        List<Pedido> listaPedidos = pedidoService.findCliente(cliente);

	        ModelAndView mv = new ModelAndView("pedido/listar_pedidos");
	        mv.addObject("listaPedidos", listaPedidos);
	        return mv;
	    }

	    @RequestMapping("/pedido/listar_pendente")
	    public ModelAndView listarPendente() {

	        List<Pedido> listaPedidos = pedidoService.findAll();

	        ModelAndView mv = new ModelAndView("/pedido/listar_pendente");
	        mv.addObject("listaPedidos", listaPedidos);
	        return mv;
	    }

	    @RequestMapping("/pedido/enviar/{id}")
	    public ModelAndView enviar(@PathVariable Long id) {

	        Pedido pedido = pedidoService.serchById(id);

	        pedido.setPendente(0);
	        pedidoService.save(pedido);

//	        ModelAndView mv = new ModelAndView("redirect:pedido/listar_pendente");
	        return listarPendente();
	    }

	}

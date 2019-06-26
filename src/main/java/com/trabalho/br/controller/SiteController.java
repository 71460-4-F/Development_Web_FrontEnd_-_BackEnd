package com.trabalho.br.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.br.model.Cliente;
import com.trabalho.br.service.ClienteService;

@Controller
public class SiteController {
	
	@Autowired
	private ClienteService clienteService;
	
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	
//redireciona se estiver logado para home do contrario vai pra raiz	
//	@RequestMapping("/")
//	public ModelAndView index() {
//		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication(); 
//		
//		if(authentication.getAuthorities().isEmpty()) {
//		Cliente cliente = clienteService.clienteLogado();
//		ModelAndView mv = new ModelAndView("redirect:/home");
//		mv.addObject("userLogado", cliente);
//		return mv;
//		}
//		ModelAndView mv = new ModelAndView("/index");
//		return mv;
//	}
	
	@RequestMapping("/")
	public ModelAndView index() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			Cliente cliente = clienteService.clienteLogado();
			ModelAndView mv = new ModelAndView("/index");
			mv.addObject("userLogado", cliente);
			return mv;
		}
		ModelAndView mv = new ModelAndView("/index");
		return mv;
	}


	@RequestMapping("/home")
	public ModelAndView inicio() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			Cliente cliente = clienteService.clienteLogado();
			ModelAndView mv = new ModelAndView("/index");
			mv.addObject("userLogado", cliente);
			return mv;
		}
		ModelAndView mv = new ModelAndView("/index");
		return mv;
	}
	
	@RequestMapping("/sobre")
	public ModelAndView sobre() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			Cliente cliente = clienteService.clienteLogado();
			ModelAndView mv = new ModelAndView("/sobre");
			mv.addObject("userLogado", cliente);
			return mv;
		}
		ModelAndView mv = new ModelAndView("/sobre");
		return mv;
	}
	
	@RequestMapping("/contato")
	public ModelAndView contato() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			Cliente cliente = clienteService.clienteLogado();
			ModelAndView mv = new ModelAndView("/contato");
			mv.addObject("userLogado", cliente);
			return mv;
		}
		ModelAndView mv = new ModelAndView("/contato");
		return mv;
	}
	

	@RequestMapping("/pratoskk")
	public String pratos() {
		return "/pratos2";
	}
	
	@RequestMapping("/redirect")
	public ModelAndView redirecionarUsuario() {
	    Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    UserDetails user = (UserDetails) auth;

	    Cliente cliente = clienteService.buscarEmail(user.getUsername());

	    ModelAndView mv;
	    if(cliente.getRoles().get(0).getPapel().equals("ROLE_GERENTE"))
	        mv = new ModelAndView("redirect:/gerente/prato/listar");
	    else
	        mv = new ModelAndView("redirect:/pedido/selecionados");

	    return mv;
	}
	
	
	@RequestMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/entrar?logout";
	}
	



//	@RequestMapping(value = "/sair")
//	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
//		clientes.clear();
//		return "redirect:/entrar?logout";
//
//	}

}

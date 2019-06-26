package com.trabalho.br.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.br.model.Cliente;
import com.trabalho.br.model.Prato;
import com.trabalho.br.service.ClienteService;
import com.trabalho.br.service.PratoService;

@Controller
public class PratoController {
	
	@Autowired
	private ClienteService clienteService;
	
	List<Cliente> clientes = new ArrayList<Cliente>();

    @Autowired
    private PratoService pratoService;

    @RequestMapping("/gerente/prato/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView mv = new ModelAndView("/gerente/cadastro_prato");
        mv.addObject("prato", new Prato());
        return mv;
    }

    @PostMapping("/gerente/prato/salvar")
    public ModelAndView salvar(@Validated Prato prato, BindingResult result, @RequestParam(value = "imagem") MultipartFile imagem) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/gerente/cadastrar_prato");
            return mv;
        }
        pratoService.save(prato, imagem);
        ModelAndView mv = new ModelAndView("redirect:/gerente/prato/listar");
        return mv;
    }

    @GetMapping("/gerente/prato/listar")
    public ModelAndView listarGerente() {
        List<Prato> listaPratos = pratoService.listPratos();
        ModelAndView mv = new ModelAndView("/gerente/listar_pratos");
        mv.addObject("listaPratos", listaPratos);

        return mv;
    }
    
//    
//    @GetMapping("/gerente/prato/listar")
//    public ModelAndView listarPratosGerente() {
//		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication(); 
//		if(authentication.isAuthenticated()) {
//			Cliente cliente = clienteService.clienteLogado();
//			ModelAndView mv = new ModelAndView("/gerente/listar_pratos");
//			mv.addObject("userLogado", cliente);
//			
//			List<Prato> listaPratos = pratoService.listPratos();
//			mv.addObject("listaPratos", listaPratos);
//			return mv;
//		}
//			List<Prato> listaPratos = pratoService.listPratos();
//			ModelAndView mv = new ModelAndView("/gerente/listar_pratos");
//			mv.addObject("listaPratos", listaPratos);
//
//        return mv;
//    }

    @GetMapping("/pratos")
    public ModelAndView listarCliente() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			Cliente cliente = clienteService.clienteLogado();
			ModelAndView mv = new ModelAndView("/cliente/listar_pratos");
			mv.addObject("userLogado", cliente);
			
			List<Prato> listaPratos = pratoService.listPratos();
			mv.addObject("listaPratos", listaPratos);
			return mv;
		}
			List<Prato> listaPratos = pratoService.listPratos();
			ModelAndView mv = new ModelAndView("/cliente/listar_pratos");
			mv.addObject("listaPratos", listaPratos);

        return mv;
    }
    

    @RequestMapping("/gerente/prato/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        pratoService.delete(id);
        ModelAndView mv = new ModelAndView("redirect:/gerente/prato/listar");
        return mv;
    }

    @RequestMapping("/gerente/prato/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable Long id) {
        Prato prato = pratoService.serchById(id);
        ModelAndView mv = new ModelAndView("/gerente/cadastro_prato");
        mv.addObject("prato", prato);
        return mv;
    }
    

}
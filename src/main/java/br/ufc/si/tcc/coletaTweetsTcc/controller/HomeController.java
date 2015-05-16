package br.ufc.si.tcc.coletaTweetsTcc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/nova-busca")
	public String novaBusca(Model model){
		return "nova-busca";
	}
	
	@RequestMapping(value="/buscar", method = RequestMethod.POST)
	public String buscar(Model model){
		return "redirect:/";
	}
	
}

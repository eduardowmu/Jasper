package com.mballem.curso.jasper.spring.controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{	@Autowired Connection connection;
	
	@GetMapping("/")
    public String index() 
	{ return "index";
    }
	
	/*o parametro model será usado para retornar uma informação
	 *para a página.*/
	@GetMapping("/conn")
    public String myConnection(Model model) 
	{	model.addAttribute("conn", connection != null ? "Connection worked" : "Connection failt");
		return "index";
    }


}

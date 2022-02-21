package com.mballem.curso.jasperspring.controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mballem.curso.jasperspring.entity.Funcionario;
import com.mballem.curso.jasperspring.repository.FuncionarioRepository;

@Controller
public class HomeController 
{	@Autowired Connection connection;
	@Autowired FuncionarioRepository repository;
	
	@GetMapping("/")
    public String index() {return "index";}
	
	/*o parametro model será usado para retornar uma informação
	 *para a página.*/
	@GetMapping("/conn")
    public String myConnection(Model model) 
	{	model.addAttribute("conn", connection != null ? "Connection worked" : "Connection failt");
		return "index";
    }
	
	@GetMapping("/certificados")
    public String certificadoValidador(@RequestParam("cid")Long cid, Model model) 
	{	Funcionario funcionario = this.repository.findById(cid).get();
		model.addAttribute("mensagem", "Confirmamos a veracidade deste certificado, pertencente "
				+ "a " + funcionario.getNome() + " emitido em " + funcionario.getDataDemissao());
		return "index";
    }
}
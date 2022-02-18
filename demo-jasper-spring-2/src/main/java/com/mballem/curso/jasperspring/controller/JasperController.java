package com.mballem.curso.jasperspring.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.MediaType;

import com.mballem.curso.jasperspring.repository.FuncionarioRepository;
import com.mballem.curso.jasperspring.repository.NivelRepository;
import com.mballem.curso.jasperspring.service.JasperService;

@Controller
public class JasperController 
{	@Autowired JasperService service;
	@Autowired NivelRepository nivelRepository;
	@Autowired FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/relatorio/pdf/jr1")
	public void showDocument01(@RequestParam("code") String code,
								@RequestParam("action") String action,
								HttpServletResponse response) throws IOException
	{	byte[] bytes = this.service.exportarPdf(code);
		
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		
		if(action.equals("v"))
		{	/*O "inline" será o responsável para dizer ao navegador que o PDF deve ser
		 	aberto por ele. Se o navegador não tiver suporte para abrir o PDF então o
		 	download será realizado.*/
			response.setHeader("Content-disposition", "inline; filename=relatorio-" 
					+ code + ".pdf");
		}
		
		else 
		{	response.setHeader("Content-disposition", "attachment; filename=relatorio-" 
				+ code + ".pdf");
		}
		/*Método de saida */
		response.getOutputStream().write(bytes);
	}
	
	@GetMapping("/relatorio/pdf/jr9/{code}")
	public void showDocument09(@PathVariable("code") String code,
					@RequestParam(name="nivel", required=false) String nivel,
					@RequestParam(name="salario", required=false) String salario,
					HttpServletResponse response) throws IOException
	{	this.service.addParams("NIVEL", nivel.isEmpty() ? 0 : Integer.parseInt(nivel));
	
		this.service.addParams("SALARY", salario.isEmpty() ? new BigDecimal(0) : 
			new BigDecimal(salario));
		
		byte[] bytes = this.service.exportarPdf(code);
		
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		
		response.setHeader("Content-disposition", "inline; filename=relatorio-" 
					+ code + ".pdf");
		
		/*Método de saida */
		response.getOutputStream().write(bytes);
	}
	
	@GetMapping("/relatorio/pdf/jr19/{code}")
	public void showDocument19(@PathVariable("code") String code,
					@RequestParam(name="idf", required=false) Long idf,
					HttpServletResponse response) throws IOException
	{	this.service.addParams("ID_FUNCIONARIO", idf);
	
		byte[] bytes = this.service.exportarPdf(code);
		
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		
		response.setHeader("Content-disposition", "inline; filename=relatorio-" 
					+ code + ".pdf");
		
		/*Método de saida */
		response.getOutputStream().write(bytes);
	}
	
	@GetMapping("/buscar/funcionarios")
	public ModelAndView findFunctionariesByName(@RequestParam("nome") String nome)
	{	return new ModelAndView("reports", "funcionarios", 
			this.funcionarioRepository.findFuncionariosByNome(nome));
	}
	
	@ModelAttribute("niveis")
	public List<Long> getNiveis()	{return this.nivelRepository.getNiveis();}
	
	@ModelAttribute("salarios")
	public List<BigDecimal> getSalaries()	{return this.funcionarioRepository.getSalaries();}	
	
	@GetMapping("/reports")
	public String openPageReports()	{return "reports";}
}
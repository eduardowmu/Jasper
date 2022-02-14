package com.mballem.curso.jasperspring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;

import com.mballem.curso.jasperspring.service.JasperService;

@Controller
public class JasperController 
{	@Autowired JasperService service;
	
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
	
	@GetMapping("/reports")
	public String openPageReports()	{return "reports";}
}
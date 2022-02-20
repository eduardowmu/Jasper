package com.mballem.curso.jasperspring.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.HtmlResourceHandler;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;

@Service
public class JasperService 
{	@Autowired private Connection connection;
	
	/*Caminho onde estarão armazenados os arquivos .jasper*/
	private static final String JASPER_DIRECTORY = "classpath:jasper/";
	private static final String JASPER_PREFIX = "Funcionarios-";
	private static final String JASPER_SUFIX = ".jasper";
	
	private Map<String, Object> params = new HashMap<>();
	
	/*Construtor sem parametros para sempre setar o diretorio de imagem*/
	public JasperService()
	{this.params.put("IMAGEM_DIRETORIO", JASPER_DIRECTORY);}
	
	public void addParams(String key, Object obj)
	{this.params.put(key, obj);}
	
	/*Método que irá abrir o relatório.*/
	public byte[] exportarPdf(String code)
	{	byte[] bytes = null;
		try 
		{	File file = ResourceUtils.getFile(
				JASPER_DIRECTORY.concat(JASPER_PREFIX).concat(code).concat(JASPER_SUFIX));
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
			bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException | JRException e) {System.out.println(e.getMessage());}
		
		return bytes;
	}

	public HtmlExporter exportarHtml(String code, HttpServletRequest request, 
			HttpServletResponse response) 
	{	HtmlExporter htmlExporter = null;
		try 
		{	File file = ResourceUtils.getFile(
				JASPER_DIRECTORY.concat(JASPER_PREFIX).concat(code).concat(JASPER_SUFIX));
			
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), 
					params, connection);
			
			htmlExporter = new HtmlExporter();
			
			htmlExporter.setExporterInput(new SimpleExporterInput(print));
			
			//htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
			
			SimpleHtmlExporterOutput htmlExporterOutput = new SimpleHtmlExporterOutput(response.getWriter());
			
			HtmlResourceHandler resourceHandler = new WebHtmlResourceHandler(request.getContextPath() 
					+ "/image/servlet?image={0}");
			
			htmlExporterOutput.setImageHandler(resourceHandler);
			
			htmlExporter.setExporterOutput(htmlExporterOutput);
			
			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
			
		} catch (JRException | IOException e) {System.out.println(e.getMessage());}
		
		return htmlExporter;
	}	
}
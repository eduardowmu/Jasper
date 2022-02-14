package com.mballem.curso.jasperspring.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService 
{	@Autowired private Connection connection;
	
	/*Caminho onde estarão armazenados os arquivos .jasper*/
	private static final String JASPER_DIRECTORY = "classpath:jasper/";
	private static final String JASPER_PREFIX = "Funcionarios-";
	private static final String JASPER_SUFIX = ".jasper";
	
	private Map<String, Object> params = new HashMap<>();
	
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
		} 
		catch (FileNotFoundException | JRException e) {System.out.println(e.getMessage());}
		
		return bytes;
	}
}

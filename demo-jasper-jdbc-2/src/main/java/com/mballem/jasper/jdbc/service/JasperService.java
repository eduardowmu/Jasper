package com.mballem.jasper.jdbc.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperService 
{	/*Variavel de tipo Map para incluir vari�veis que ser�o
 	necess�rios nos relatórios*/
	private Map<String, Object> params = new LinkedHashMap<>();
	
	/*Metodo que irá add os parametros na nossa varável params*/
	public void addParams(String key, Object obj)
	{this.params.put(key, obj);}
	
	public void abrirJasperViewer(String jrxml, Connection connection)
	{	JasperReport report = this.compilarJrxml(jrxml);
		try 
		{	JasperPrint print = JasperFillManager
				.fillReport(report, this.params, connection);
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(true);
		} 
		catch (JRException e) {System.out.println(e.getMessage());}
	}
	
	public void exportToPdf(String jrxml, Connection connection, String docPath)
	{	JasperReport report = this.compilarJrxml(jrxml);
		try 
		{	OutputStream outputStream = new FileOutputStream(docPath);
			JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
			JasperExportManager.exportReportToPdfStream(print, outputStream);
		} 
		catch (JRException | FileNotFoundException e) {System.out.println(e.getMessage());}
	}
	
	/*Método de compilaç*/
	private JasperReport compilarJrxml(String arquivo)
	{	/*Instrução que vai nos retornar um InputStream*/
		try 
		{	InputStream inputStream = new FileInputStream(arquivo);//getClass().getClassLoader().getResourceAsStream(arquivo);
			return JasperCompileManager.compileReport(inputStream);
		} 
		catch (JRException | FileNotFoundException e) {System.out.println(e.getMessage());}
		return null;
	}
}

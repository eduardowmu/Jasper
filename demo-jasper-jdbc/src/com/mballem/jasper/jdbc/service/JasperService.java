package com.mballem.jasper.jdbc.service;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

public class JasperService 
{	/*Variavel de tipo Map para incluir variáveis que serão
 	necessários nos relatórios*/
	private Map<String, Object> params = new LinkedHashMap<>();
	
	/*Metodo que irá add os parametros na nossa varável params*/
	public void addParams(String key, Object obj)
	{this.params.put(key, obj);}
	
	/*Método de compilação*/
	private JasperReport compilarJrxml(String arquivo)
	{	/*Instrução que vai nos retornar um InputStream*/
		try 
		{	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(arquivo);
			return JasperCompileManager.compileReport(inputStream);
		} 
		catch (JRException e) {System.out.println(e.getMessage());}
		return null;
	}
}

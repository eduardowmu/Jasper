package com.mballem.curso.jasperspring.config;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.jasperreports.j2ee.servlets.ImageServlet;
@Configuration
public class ConfiguracaoGenerica 
{	/*O objeto dataSource que irá conter a conexão com o banco
 	de dados baseadas nas configurações que temos no arquivo
 	de propriedades.*/
	@Bean
	public Connection connection(DataSource dataSource) throws SQLException
	{return dataSource.getConnection();}
	
	/*A classe abaixo é uma classe do spring que serve para que registremos 
	 *Servlets. NO spring já existe um Servlet padrão que é o despacher servlet
	 *que é o servlet que usamos para termos acesso às nossas páginas com
	 *SpringMVC.*/
	@Bean
	public ServletRegistrationBean imageServlet()
	{	ServletRegistrationBean servlet = new ServletRegistrationBean(
			/*Servirá para toda imagem que deverá ser processada*/
			new ImageServlet(), "/image/servlet/*");
		
		/*Ajustando para que o servet tenha prioridade de ordem 1*/
		servlet.setLoadOnStartup(1);
		
		return servlet;
	}
}
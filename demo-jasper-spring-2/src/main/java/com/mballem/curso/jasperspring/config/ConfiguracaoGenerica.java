package com.mballem.curso.jasperspring.config;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
public class ConfiguracaoGenerica 
{	/*O objeto dataSource que irá conter a conexão com o banco
 	de dados baseadas nas configurações que temos no arquivo
 	de propriedades.*/
	@Bean
	public Connection connection(DataSource dataSource) throws SQLException
	{return dataSource.getConnection();}
}
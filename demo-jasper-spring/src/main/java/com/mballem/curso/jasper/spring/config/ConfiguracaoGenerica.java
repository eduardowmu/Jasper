package com.mballem.curso.jasper.spring.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Essa notação serve para transformar a classe em uma classe
 *de configuração, então quando subimos nossa aplicação, as
 *primeiras classes que são inicializadas pelo spring são as
 *classes de configuração. Dessa forma, logo que a aplicação 
 *sobe ja criamos nosso objeto de conexão.*/
@Configuration
public class ConfiguracaoGenerica 
{	/*O objeto dataSource que irá conter a conexão com o banco
 	de dados baseadas nas configurações que temos no arquivo
 	de propriedades.*/
	@Bean
	public Connection connection(DataSource dataSource) throws SQLException
	{return dataSource.getConnection();}
}
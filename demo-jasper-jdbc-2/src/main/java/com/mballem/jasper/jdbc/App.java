package com.mballem.jasper.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.mballem.jasper.jdbc.conn.*;
import com.mballem.jasper.jdbc.service.JasperService;

public class App 
{	public static void main( String[] args ) throws SQLException
    {abrirJrxml("01");}

	private static void abrirJrxml(String numero) throws SQLException
	{	Connection connection = JdbcConnection.connection();
		JasperService service = new JasperService();
		service.abrirJasperViewer("H:\\Dados\\MEU PC\\Meusdoc\\UDEMY\\JasperReports\\repositorio\\Jasper\\demo-jasper-jdbc-2\\src\\main\\resources\\relatorios\\Funcionarios-" + numero + ".jrxml", connection);
		connection.close();
	}
}

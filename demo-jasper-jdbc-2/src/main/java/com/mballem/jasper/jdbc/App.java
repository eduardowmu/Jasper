package com.mballem.jasper.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.mballem.jasper.jdbc.conn.*;
import com.mballem.jasper.jdbc.service.JasperService;
/*https://stackoverflow.com/questions/20991641/jasper-compiler-error-when-using-sum-method*/
public class App 
{	public static void main( String[] args ) throws SQLException
    {abrirJrxml("09");}

	private static void abrirJrxml(String numero) throws SQLException
	{	Connection connection = JdbcConnection.connection();
		JasperService service = new JasperService();
		/*método inserido previamente na classe de serviço (JasperService)*/
		service.addParams("NIVEL", 1);
		service.addParams("SALARY", new BigDecimal(10));
		service.abrirJasperViewer("src/main/resources/relatorios/Funcionarios-" + numero + ".jrxml", connection);
		connection.close();
	}
}

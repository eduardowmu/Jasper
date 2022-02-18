package com.mballem.curso.jasperspring.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mballem.curso.jasperspring.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> 
{	@Query("select distinct f.salario from Funcionario f order by f.salario asc")
	List<BigDecimal> getSalaries();

	@Query("select distinct f.id from Funcionario f order by f.nivel asc")
	List<Long> getNiveis();
	
	/*O Tuple é uma interface que retorna uma fila de registros de
	 *uma entidade de uma tabela*/
	@Query("select f.id as id, f.nome as nome "
			+ "from Funcionario f "
			+ "where f.nome like %:nome% "
			+ "and f.dataDemissao is not null "
			+ "and f.nivel.id = 1")
	List<Tuple> findFuncionariosByNome(String nome);
}
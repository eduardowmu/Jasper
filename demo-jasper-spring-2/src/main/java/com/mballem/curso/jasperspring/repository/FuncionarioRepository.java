package com.mballem.curso.jasperspring.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mballem.curso.jasperspring.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> 
{	@Query("select distinct f.salario from Funcionario f order by f.salario asc")
	List<BigDecimal> getSalaries();	
}
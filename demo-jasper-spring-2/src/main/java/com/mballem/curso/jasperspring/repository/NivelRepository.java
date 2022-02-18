package com.mballem.curso.jasperspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mballem.curso.jasperspring.entity.Nivel;

public interface NivelRepository extends JpaRepository<Nivel, Long> 
{	@Query("select n.id from Nivel n order by n.id asc")
	List<Long> getNiveis();
}

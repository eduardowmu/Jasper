package com.mballem.curso.jasper.spring.repository;

import com.mballem.curso.jasper.spring.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select f.id as id, f.nome as nome " +
            "from Funcionario f " +
            "where f.nome like %:nome% " +
            "AND f.dataDemissao is not null " +
            "AND f.nivel.id = 1")
    List<Tuple> findFuncionariosByNome(String nome);
}

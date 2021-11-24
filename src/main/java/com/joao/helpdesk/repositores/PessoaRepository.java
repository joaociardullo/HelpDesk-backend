package com.joao.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.helpdesk.domain.Pessoa;
//vai fazer a persitencia la no banco 


public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}

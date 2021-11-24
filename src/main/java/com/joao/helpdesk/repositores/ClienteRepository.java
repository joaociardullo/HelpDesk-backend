package com.joao.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.helpdesk.domain.Cliente;

//vai fazer a persitencia la no banco 


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

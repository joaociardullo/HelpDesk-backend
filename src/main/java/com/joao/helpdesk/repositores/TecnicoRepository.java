package com.joao.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

//vai fazer a persitencia la no banco 
import com.joao.helpdesk.domain.Tecnico;


public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}

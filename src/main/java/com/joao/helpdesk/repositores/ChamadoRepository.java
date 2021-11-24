package com.joao.helpdesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.helpdesk.domain.Chamado;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}

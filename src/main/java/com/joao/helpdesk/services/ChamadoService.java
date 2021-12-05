package com.joao.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.helpdesk.domain.Chamado;
import com.joao.helpdesk.repositores.ChamadoRepository;
import com.joao.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj =repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encotrado" + id));
	}


	public List<Chamado> findAll() {

		return repository.findAll();
	}
	
	
}

package com.joao.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.helpdesk.domain.Tecnico;
import com.joao.helpdesk.repositores.TecnicoRepository;
import com.joao.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		 Optional<Tecnico> obj = repository.findById(id);
		 return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto nao encotrado:" + id));  // Se não encontrar retorna null por enquanto
	}

	public List<Tecnico> findAll() {

		return repository.findAll();
	}
}

package com.joao.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.helpdesk.domain.Tecnico;
import com.joao.helpdesk.repositores.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		 Optional<Tecnico> obj = repository.findById(id);
		 return obj.orElse(null);  // Se não encontrar retorna null por enquanto
	}
}

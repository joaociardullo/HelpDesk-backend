package com.joao.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.helpdesk.domain.Pessoa;
import com.joao.helpdesk.domain.Tecnico;
import com.joao.helpdesk.domain.dtos.TecnicoDTO;
import com.joao.helpdesk.repositores.PessoaRepository;
import com.joao.helpdesk.repositores.TecnicoRepository;
import com.joao.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.joao.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encotrado:" + id)); // Se não encontrar
																									// retorna null por
																									// enquanto
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {

		objDTO.setId(null); // esse ID vai vim nullo
		ValidaPorCPFEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);

	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		ValidaPorCPFEEmail(objDTO);
		oldObj =new Tecnico(objDTO);
		return repository.save(oldObj);
	}
	
	//Deletando pelo ID 
	public void delete(Integer id) {
		Tecnico obj =findById(id);
		if(obj.getChamados().size()>0) {
			throw new DataIntegrityViolationException("O tecnico possui ordem de serviço e nao pode ser deletado !!"); //Tratando a violação
		}
			repository.deleteById(id);
		
	}

	/*
	 *METODO PARA vALIDAR O CPF E EMAIL ATRAVEIS DE OBJ E MANIPULANDO COM POSTMAN  
	 * FOI CRIADA DataIntegrityViolationExceptioN
	 * */
	private void ValidaPorCPFEEmail(TecnicoDTO objDTO) {

		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado no sistema !");
			
		}
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail ja cadastrado no sistema !");
			
		}
	}

	
}

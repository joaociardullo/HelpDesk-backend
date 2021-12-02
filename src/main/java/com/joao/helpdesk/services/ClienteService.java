package com.joao.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.helpdesk.domain.Pessoa;
import com.joao.helpdesk.domain.Cliente;
import com.joao.helpdesk.domain.dtos.ClienteDTO;
import com.joao.helpdesk.repositores.PessoaRepository;
import com.joao.helpdesk.repositores.ClienteRepository;
import com.joao.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.joao.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encotrado:" + id)); // Se não encontrar
																									// retorna null por
																									// enquanto
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {

		objDTO.setId(null); // esse ID vai vim nullo
		ValidaPorCPFEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);

	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		ValidaPorCPFEEmail(objDTO);
		oldObj =new Cliente(objDTO);
		return repository.save(oldObj);
	}
	
	//Deletando pelo ID 
	public void delete(Integer id) {
		Cliente obj =findById(id);
		if(obj.getChamados().size()>0) {
			throw new DataIntegrityViolationException("O Cliente possui ordem de serviço e nao pode ser deletado !!"); //Tratando a violação
		}
			repository.deleteById(id);
		
	}

	/*
	 *METODO PARA vALIDAR O CPF E EMAIL ATRAVEIS DE OBJ E MANIPULANDO COM POSTMAN  
	 * FOI CRIADA DataIntegrityViolationExceptioN
	 * */
	private void ValidaPorCPFEEmail(ClienteDTO objDTO) {

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

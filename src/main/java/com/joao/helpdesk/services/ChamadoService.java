package com.joao.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.helpdesk.domain.Chamado;
import com.joao.helpdesk.domain.Cliente;
import com.joao.helpdesk.domain.Tecnico;
import com.joao.helpdesk.domain.dtos.ChamadoDTO;
import com.joao.helpdesk.domain.enums.Prioridade;
import com.joao.helpdesk.domain.enums.Status;
import com.joao.helpdesk.repositores.ChamadoRepository;
import com.joao.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encotrado" + id));
	}

	public List<Chamado> findAll() {

		return repository.findAll();
	}

	// CREATE CHAMADO

	public Chamado create(@Valid ChamadoDTO objDTO) {

		return repository.save(newChamado(objDTO));
	}

	//update do chamado
	public Chamado update(Integer id, ChamadoDTO objDTO) {
		objDTO.setCliente(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);

		return repository.save(oldObj);
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());

		Chamado chamado = new Chamado();
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		//tratando 1 -aberto 2 encerrado
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}

}

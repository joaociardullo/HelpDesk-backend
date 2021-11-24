package com.joao.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.helpdesk.domain.Chamado;
import com.joao.helpdesk.domain.Cliente;
import com.joao.helpdesk.domain.Tecnico;
import com.joao.helpdesk.domain.enums.Perfil;
import com.joao.helpdesk.domain.enums.Prioridade;
import com.joao.helpdesk.domain.enums.Status;
import com.joao.helpdesk.repositores.ChamadoRepository;
import com.joao.helpdesk.repositores.ClienteRepository;
import com.joao.helpdesk.repositores.TecnicoRepository;

@Service // Um servico ||| Injeçaõ de Depedencia
public class DBService {

	@Autowired // gerenciar
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {

		Tecnico tec1 = new Tecnico(null, "Joao", "233.951.910-10", "joao@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus silva", "233.951.910-10 ", "linus@email.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1,
				cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}

package com.joao.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joao.helpdesk.domain.dtos.ClienteDTO;
import com.joao.helpdesk.domain.enums.Perfil;

@Entity
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;

	@JsonIgnore // Serve nao hora do postman, para nao da serialização ai ele igonora pois fica
				// em um loop infinio
	@OneToMany(mappedBy = "cliente") // Um tecinoco para muitos chamados
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE); // criar um novo cliente ele ja vem com o perfil
	}

	// Construtor
	public Cliente(ClienteDTO obj) {
			super();
			this.id = obj.getId();
			this.nome = obj.getNome();
			this.cpf = obj.getCpf();
			this.email = obj.getEmail();
			this.senha = obj.getSenha();
			//convetendo a lista de perfis pois le é um Interger
			this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
			this.dataCriacao = obj.getDataCriacao();
		}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE); // adicionei aqui no construtor
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}

package com.joao.helpdesk.domain.dtos;
/*
 * eSSA CLASSE SERVE PARA PARA TRANSFERENCIA DE DADOS DTO
 * OBJETO DE TRANSFERENCIA DE DADOS DTO
 * 
 * */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joao.helpdesk.domain.Tecnico;
import com.joao.helpdesk.domain.enums.Perfil;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "O campo NOME é requerido") //nao aceita esse campo nullo
	protected String nome;
	@NotNull(message = "O campo CPF é requerido")
	protected String cpf;
	@NotNull(message = "O campo Email é requerido")
	protected String email;
	@NotNull(message = "O campo Senha é requerido")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>(); // nao permite dois valores iguais

	@JsonFormat(pattern = "dd/MM/yyyy") // Formatar a data
	protected LocalDate dataCriacao = LocalDate.now();

	// CONSTRUTOR DA SUPER CLASSE
	public TecnicoDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	//Construtor
	public TecnicoDTO(Tecnico obj) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

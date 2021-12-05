package com.joao.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joao.helpdesk.domain.Chamado;

public class ChamadoDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy") // Formatar a data
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datafechamamento;
	@NotNull(message = "O campo  prioridade é requerido")
	private Integer prioridade;
	@NotNull(message = "O campo  Status é requerido")
	private Integer status;
	@NotNull(message = "O campo  Titulo é requerido")
	private String titulo;
	@NotNull(message = "O campo  Observação é requerido")
	private String observacoes;
	@NotNull(message = "O campo  Tecnico é requerido")
	private Integer tecnico;
	@NotNull(message = "O campo  Cliente é requerido")
	private Integer cliente;
	private String nomeTecnico;
	private String nomeCliente;

	public ChamadoDTO() {
		super();

	}

	public ChamadoDTO(Chamado obj) {
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.datafechamamento = obj.getDatafechamamento();
		this.prioridade = obj.getPrioridade().getCodigo();
		this.status = obj.getStatus().getCodigo();
		this.titulo = obj.getTitulo();
		this.observacoes = obj.getObservacoes();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeCliente = obj.getCliente().getNome();
		this.nomeTecnico = obj.getTecnico().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDatafechamamento() {
		return datafechamamento;
	}

	public void setDatafechamamento(LocalDate datafechamamento) {
		this.datafechamamento = datafechamamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}

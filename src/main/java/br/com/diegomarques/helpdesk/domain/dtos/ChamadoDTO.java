package br.com.diegomarques.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.diegomarques.helpdesk.domain.Chamado;
import jakarta.validation.constraints.NotNull;

public class ChamadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataFechamento;
	@NotNull(message = "o Campo Prioridade é requerido")
	private Integer prioridade;
	@NotNull(message = "o Campo Prioridade é requerido")
	private Integer status;
	@NotNull(message = "o Campo Status é requerido")
	private String titulo;
	@NotNull(message = "o Campo Observações é requerido")
	private String observacoes; 
	@NotNull(message = "o Campo Tecnico é requerido")
	private Integer tecnico;
	@NotNull(message = "o Campo Cliente é requerido")
	private Integer cliente;
	
	private String nomeTecnico;
	private String nomeCliente;
	
	
	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.prioridade = chamado.getPrioridade().getCodigo();
		this.status = chamado.getStatus().getCodigo();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.tecnico = chamado.getTecnico().getId();
		this.cliente = chamado.getCliente().getId();
		this.nomeTecnico = chamado.getTecnico().getNome();
		this.nomeCliente = chamado.getCliente().getNome();
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

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
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

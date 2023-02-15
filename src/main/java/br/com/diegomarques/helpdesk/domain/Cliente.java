package br.com.diegomarques.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.diegomarques.helpdesk.domain.dtos.ClienteDTO;
import br.com.diegomarques.helpdesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore // Proteger da Serialização
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>(); //Iniciar o ArrayList para evitar ponteiro nulo

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(ClienteDTO clienteDTO) {
		super();
		this.id = clienteDTO.getId();
		this.nome = clienteDTO.getNome();
		this.cpf = clienteDTO.getCpf();
		this.email = clienteDTO.getEmail();
		this.senha = clienteDTO.getSenha();
		this.perfis = clienteDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = clienteDTO.getDataCriacao();
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	
	
}

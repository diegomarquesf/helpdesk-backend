package br.com.diegomarques.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.diegomarques.helpdesk.domain.enums.Perfil;

public class Cliente extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	private List<Chamado> chamados = new ArrayList<>(); //Iniciar o ArrayList para evitar ponteiro nulo

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	
	
}

package br.com.diegomarques.helpdesk.domain;

import br.com.diegomarques.helpdesk.domain.enums.Perfil;

public class Tecnico extends Pessoa{
	private static final long serialVersionUID = 1L;

	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}
	
	

}

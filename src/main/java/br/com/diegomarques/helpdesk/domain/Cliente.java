package br.com.diegomarques.helpdesk.domain;

import br.com.diegomarques.helpdesk.domain.enums.Perfil;

public class Cliente extends Pessoa{
	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	
	
}

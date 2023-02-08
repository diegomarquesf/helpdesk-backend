package br.com.diegomarques.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegomarques.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}

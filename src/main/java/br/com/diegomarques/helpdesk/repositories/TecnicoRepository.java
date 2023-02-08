package br.com.diegomarques.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegomarques.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}

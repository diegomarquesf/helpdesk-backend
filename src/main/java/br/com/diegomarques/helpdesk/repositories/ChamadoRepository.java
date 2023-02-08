package br.com.diegomarques.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegomarques.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}

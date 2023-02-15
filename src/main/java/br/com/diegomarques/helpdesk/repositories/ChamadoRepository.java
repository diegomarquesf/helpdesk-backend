package br.com.diegomarques.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegomarques.helpdesk.domain.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}

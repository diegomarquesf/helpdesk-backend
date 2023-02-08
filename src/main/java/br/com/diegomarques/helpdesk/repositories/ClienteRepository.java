package br.com.diegomarques.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegomarques.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}

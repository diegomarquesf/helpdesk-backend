package br.com.diegomarques.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegomarques.helpdesk.domain.Tecnico;
import br.com.diegomarques.helpdesk.repositories.TecnicoRepository;
import br.com.diegomarques.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado, ID: " +id));
	}
	
	public List<Tecnico> findAll(){
		return tecnicoRepository.findAll();
	}

}

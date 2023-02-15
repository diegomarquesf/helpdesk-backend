package br.com.diegomarques.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegomarques.helpdesk.domain.Chamado;
import br.com.diegomarques.helpdesk.repositories.ChamadoRepository;
import br.com.diegomarques.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado; ID: " + id));
	}

}

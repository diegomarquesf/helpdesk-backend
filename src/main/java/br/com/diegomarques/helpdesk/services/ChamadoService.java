package br.com.diegomarques.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegomarques.helpdesk.domain.Chamado;
import br.com.diegomarques.helpdesk.domain.Cliente;
import br.com.diegomarques.helpdesk.domain.Tecnico;
import br.com.diegomarques.helpdesk.domain.dtos.ChamadoDTO;
import br.com.diegomarques.helpdesk.domain.enums.Prioridade;
import br.com.diegomarques.helpdesk.domain.enums.Status;
import br.com.diegomarques.helpdesk.repositories.ChamadoRepository;
import br.com.diegomarques.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired 
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
	
	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado; ID: " + id));
	}

	public List<Chamado> findall() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(newChamado(chamadoDTO));
	}
	
	private Chamado newChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
		Cliente cliente = clienteService.findByid(chamadoDTO.getCliente());
		
		Chamado chamado = new Chamado();
		if(chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		
		return chamado;
		
	}

}

package br.com.diegomarques.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegomarques.helpdesk.domain.Pessoa;
import br.com.diegomarques.helpdesk.domain.Tecnico;
import br.com.diegomarques.helpdesk.domain.dtos.TecnicoDTO;
import br.com.diegomarques.helpdesk.repositories.PessoaRepository;
import br.com.diegomarques.helpdesk.repositories.TecnicoRepository;
import br.com.diegomarques.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.diegomarques.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado, ID: " +id));
	}
	
	public List<Tecnico> findAll(){
		return tecnicoRepository.findAll();
	}

	public Tecnico create(@Valid TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validaPorCpfEEmail(tecnicoDTO);
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		
		return tecnicoRepository.save(tecnico);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(id);
		Tecnico oldTecnico = findById(id);
		validaPorCpfEEmail(tecnicoDTO);
		oldTecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(oldTecnico);
	}
	

	private void validaPorCpfEEmail(@Valid TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) { //Validação Para CPF
			throw new DataIntegrityViolationException("CPF já cadastrado no Sistema!");
		}
		
		pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {  //Validação para E-EMAIL
			throw new DataIntegrityViolationException("E-MAIL já cadastrado no Sistema!");
		}
	}



}

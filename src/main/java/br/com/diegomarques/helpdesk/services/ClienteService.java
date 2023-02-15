package br.com.diegomarques.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegomarques.helpdesk.domain.Cliente;
import br.com.diegomarques.helpdesk.domain.Pessoa;
import br.com.diegomarques.helpdesk.domain.dtos.ClienteDTO;
import br.com.diegomarques.helpdesk.repositories.ClienteRepository;
import br.com.diegomarques.helpdesk.repositories.PessoaRepository;
import br.com.diegomarques.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.diegomarques.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findByid(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		 
		 return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado; ID: " +id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		validaPorCpfEEmail(clienteDTO);
		Cliente cliente = new Cliente(clienteDTO);
		
		return clienteRepository.save(cliente);
	}

	public Cliente update(Integer id, ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		Cliente oldCliente = findByid(id);
		validaPorCpfEEmail(clienteDTO);
		oldCliente = new Cliente(clienteDTO);
		return clienteRepository.save(oldCliente);
	}

	public void delete(Integer id) {
		Cliente cliente = findByid(id);
		if(cliente.getChamados().size() > 0 ) {
			throw new DataIntegrityViolationException("Não pode ser excluido, porque há chamados");
		}
		clienteRepository.deleteById(id);
	}
	
	private void validaPorCpfEEmail(@Valid ClienteDTO clienteDTO){
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) { 
			throw new DataIntegrityViolationException("CPF já cadastrado no Sistema!");
		}
		
		pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) { 
			throw new DataIntegrityViolationException("E-MAIL já cadastrado no Sistema!");
		}
	}

}

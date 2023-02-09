package br.com.diegomarques.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegomarques.helpdesk.domain.Chamado;
import br.com.diegomarques.helpdesk.domain.Cliente;
import br.com.diegomarques.helpdesk.domain.Tecnico;
import br.com.diegomarques.helpdesk.domain.enums.Perfil;
import br.com.diegomarques.helpdesk.domain.enums.Prioridade;
import br.com.diegomarques.helpdesk.domain.enums.Status;
import br.com.diegomarques.helpdesk.repositories.ChamadoRepository;
import br.com.diegomarques.helpdesk.repositories.ClienteRepository;
import br.com.diegomarques.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {

		Tecnico tecnico = new Tecnico(null, "Diego Tecnico", "22975484003", "diego_tecnico@hotmail.com", "123");
		tecnico.addPerfil(Perfil.ADMIN);

		Cliente cliente = new Cliente(null, "Fulano 1", "40237178001", "fulano1@mail.com", "123");

		Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tecnico,
				cliente);

		tecnicoRepository.saveAll(Arrays.asList(tecnico));
		clienteRepository.saveAll(Arrays.asList(cliente));
		chamadoRepository.saveAll(Arrays.asList(chamado));

	}
}

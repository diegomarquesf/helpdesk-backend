package br.com.diegomarques.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.diegomarques.helpdesk.domain.Cliente;
import br.com.diegomarques.helpdesk.domain.dtos.ClienteDTO;
import br.com.diegomarques.helpdesk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

		@Autowired
		private ClienteService clienteService;
		
	//FINDBYID
		@GetMapping(value = "/{id}")
		public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
			Cliente cliente = clienteService.findByid(id); 
			;
			return ResponseEntity.ok().body(new ClienteDTO(cliente));
		}
	
	//FINDALL
		@GetMapping
		public ResponseEntity<List<ClienteDTO>> findAll(){
			List<Cliente> list = clienteService.findAll();
			List<ClienteDTO> listDTO = list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
			
			return ResponseEntity.ok().body(listDTO);
		}
	
	//CREATE
		@PostMapping
		public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO){
			Cliente cliente = clienteService.create(clienteDTO);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
	
	//UPDATE
		@PutMapping(value = "/{id}")
		public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO){
			Cliente oldCliente = clienteService.update(id, clienteDTO);
			
			return ResponseEntity.ok().body(new ClienteDTO(oldCliente));
		}
	
	//DELETE
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			clienteService.delete(id);
			
			return ResponseEntity.noContent().build();
		}
}

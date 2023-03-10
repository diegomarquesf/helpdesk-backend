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

import br.com.diegomarques.helpdesk.domain.Tecnico;
import br.com.diegomarques.helpdesk.domain.dtos.TecnicoDTO;
import br.com.diegomarques.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico tecnico = tecnicoService.findById(id);
		
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = tecnicoService.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@RequestBody @Valid TecnicoDTO tecnicoDTO){
		Tecnico tecnico = tecnicoService.create(tecnicoDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id ,@Valid @RequestBody TecnicoDTO tecnicoDTO){
		Tecnico oldTecnico = tecnicoService.update(id, tecnicoDTO);
		
		return ResponseEntity.ok().body(new TecnicoDTO(oldTecnico));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
		tecnicoService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}

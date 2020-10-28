package com.restaurante.api.resources;

import java.util.List;
import java.util.Optional;

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

import com.restaurante.api.model.Cozinha;
import com.restaurante.api.repositories.CozinhaRespository;
import com.restaurante.api.services.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaResource {

	@Autowired
	private CozinhaRespository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public ResponseEntity<List<Cozinha>> findAll(){
		
		List<Cozinha> list = cozinhaService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findyById(@PathVariable Long id){
		
		Optional<Cozinha> obj = cozinhaRepository.findById(id);
		return ResponseEntity.ok().body(obj.orElse(null));
		
	}
	
	@PostMapping
	public ResponseEntity<Cozinha> insert(@RequestBody Cozinha cozinha){
		
		return ResponseEntity.ok().body(cozinhaService.insert(cozinha));		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@PathVariable Long id, @RequestBody Cozinha cozinha){
		if(!cozinhaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		var obj = new Cozinha();
		obj.setId(id);
		obj = cozinha;
		obj = cozinhaService.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		if(!cozinhaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cozinhaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	

}

package com.restaurante.api.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.domain.repositories.RestauranteRepository;
import com.restaurante.api.domain.services.RestauranteService;

@Controller
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public ResponseEntity<List<Restaurante>> findAll() {
		List<Restaurante> list = restauranteRepository.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> find(@PathVariable Long id) {
		Restaurante restaurante = restauranteRepository.find(id);

		if (restaurante != null) {
			return ResponseEntity.ok().body(restaurante);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Restaurante restaurante) {

		try {
			Restaurante restauranteSalvo = restauranteService.save(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Restaurante restaurante, @PathVariable Long id) {

		try {
			Restaurante restauranteAtual = restauranteRepository.find(id);

			if (restauranteAtual != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
				restauranteAtual = restauranteService.save(restauranteAtual);

				return ResponseEntity.ok(restauranteAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

		Restaurante restauranteAtual = restauranteRepository.find(id);

		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}
				
		merge(campos, restauranteAtual);
		return update(restauranteAtual, id);
	}
	
	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restaurante = objectMapper.convertValue(camposOrigem, Restaurante.class);
		
		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restaurante);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
			
			System.out.println(nomePropriedade + " = " + " " + valorPropriedade + " = " + novoValor);
		});
	}

}

package com.restaurante.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.restaurante.api.model.Cozinha;
import com.restaurante.api.repositories.CozinhaRespository;

@SpringBootApplication
public class RestauranteApiApplication implements CommandLineRunner{

	@Autowired
	private CozinhaRespository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(RestauranteApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		var c1 = new Cozinha(null, "Cozinha da Nona");
		var c2 = new Cozinha(null, "Cozinha do nono");
		
		repo.saveAll(Arrays.asList(c1, c2));
	}

}

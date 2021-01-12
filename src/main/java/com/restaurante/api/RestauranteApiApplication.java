package com.restaurante.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.restaurante.api.infraestructure.repositories.CustomJPARepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJPARepositoryImpl.class)
public class RestauranteApiApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(RestauranteApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		  
	}

}

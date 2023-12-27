package br.com.GerenciadorPetshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;

@SpringBootApplication
@EnableMBeanExport
public class GerenciadorPetshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPetshopApplication.class, args);
	}

}

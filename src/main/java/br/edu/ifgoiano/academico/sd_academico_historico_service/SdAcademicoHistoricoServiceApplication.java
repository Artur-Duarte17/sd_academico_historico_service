package br.edu.ifgoiano.academico.sd_academico_historico_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Aplicação Spring Boot para o Serviço de Histórico Acadêmico.
 * Implementa o cliente de descoberta de serviços (Eureka) e registra
 * eventos acadêmicos através de mensagens RabbitMQ.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SdAcademicoHistoricoServiceApplication {

	/**
	 * Ponto de entrada da aplicação
	 */
	public static void main(String[] args) {
		SpringApplication.run(SdAcademicoHistoricoServiceApplication.class, args);
	}

}

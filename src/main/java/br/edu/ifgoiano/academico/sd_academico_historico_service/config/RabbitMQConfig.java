package br.edu.ifgoiano.academico.sd_academico_historico_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Declarables;

/**
 * Configuração do RabbitMQ para o Histórico Service.
 * Define as filas, exchanges e bindings para consumir eventos acadêmicos.
 */
@Configuration
public class RabbitMQConfig {

    // Nome do exchange para publicação de eventos (Pub/Sub)
    public static final String EXCHANGE_ACADEMICO = "academico.events";
    
    // Chaves de roteamento para eventos
    public static final String ROUTING_KEY_MATRICULA_CRIADA = "matricula.criada";
    public static final String ROUTING_KEY_MATRICULA_CANCELADA = "matricula.cancelada";

    // Fila para consumir eventos de histórico
    public static final String QUEUE_EVENTO_HISTORICO = "queue.evento.historico";

    /**
     * Define a fila para eventos de histórico (Pub/Sub)
     */
    @Bean
    public Queue queueEventoHistorico() {
        return new Queue(QUEUE_EVENTO_HISTORICO, true);
    }

    /**
     * Define o exchange de tipo Topic para publicação de eventos
     */
    @Bean
    public TopicExchange academicoExchange() {
        return new TopicExchange(EXCHANGE_ACADEMICO, true, false);
    }

    /**
     * Binding: conecta a fila ao exchange para eventos de matrícula criada
     */
    @Bean
    public Binding bindingMatriculaCriada(Queue queueEventoHistorico, TopicExchange academicoExchange) {
        return BindingBuilder.bind(queueEventoHistorico)
                .to(academicoExchange)
                .with(ROUTING_KEY_MATRICULA_CRIADA);
    }

    /**
     * Binding: conecta a fila ao exchange para eventos de matrícula cancelada
     */
    @Bean
    public Binding bindingMatriculaCancelada(Queue queueEventoHistorico, TopicExchange academicoExchange) {
        return BindingBuilder.bind(queueEventoHistorico)
                .to(academicoExchange)
                .with(ROUTING_KEY_MATRICULA_CANCELADA);
    }

    @Bean
    public Declarables loggingDeclarables() {
        TopicExchange logsExchange = new TopicExchange("logs.academico", true, false);
        Queue logsQueue = new Queue("logs.all", true);
        Binding logsBinding = BindingBuilder.bind(logsQueue).to(logsExchange).with("logs.#");
        return new Declarables(logsExchange, logsQueue, logsBinding);
    }
}


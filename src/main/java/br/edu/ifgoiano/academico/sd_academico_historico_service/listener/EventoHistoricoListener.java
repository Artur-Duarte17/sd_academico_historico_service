package br.edu.ifgoiano.academico.sd_academico_historico_service.listener;

import br.edu.ifgoiano.academico.sd_academico_historico_service.entity.HistoricoAcademico;
import br.edu.ifgoiano.academico.sd_academico_historico_service.repository.HistoricoAcademicoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Listener para consumir eventos no padrão Pub/Sub do RabbitMQ.
 * Recebe eventos de negócio (matrícula criada/cancelada) e os registra no histórico acadêmico.
 */
@Service
public class EventoHistoricoListener {

    // Logger para rastreamento de operações
    private static final Logger logger = LoggerFactory.getLogger(EventoHistoricoListener.class);
    
    private final HistoricoAcademicoRepository historicoRepository;
    private final ObjectMapper objectMapper;

    /**
     * Construtor com injeção de dependências
     */
    public EventoHistoricoListener(HistoricoAcademicoRepository historicoRepository, ObjectMapper objectMapper) {
        this.historicoRepository = historicoRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Consome eventos da fila queue.evento.historico vinculada ao exchange academico.events
     * @param mensagem - JSON com os dados do evento
     */
    @RabbitListener(queues = "queue.evento.historico")
    public void consumirEvento(String mensagem) {
        try {
            logger.info("[HISTORICO-SERVICE] Evento recebido: {}", mensagem);

            // Desserializa a mensagem JSON do evento
            JsonNode jsonNode = objectMapper.readTree(mensagem);

            // Extrai os dados do evento
            Long alunoId = jsonNode.get("alunoId").asLong();
            Long turmaId = jsonNode.get("turmaId").asLong();
            String tipo = jsonNode.get("tipo").asText();
            String descricao = jsonNode.get("descricao").asText();

            // Cria a entidade de histórico a partir do evento
            HistoricoAcademico historico = new HistoricoAcademico(
                    alunoId,
                    turmaId,
                    tipo,
                    descricao
            );

            // Persiste no banco de dados
            historicoRepository.save(historico);

            logger.info("[HISTORICO-SERVICE] Evento registrado no histórico - ID: {}, Aluno: {}, Tipo: {}, Descrição: {}",
                    historico.getId(), alunoId, tipo, descricao);

        } catch (Exception e) {
            logger.error("[HISTORICO-SERVICE] Erro ao processar evento: {}", e.getMessage(), e);
        }
    }
}


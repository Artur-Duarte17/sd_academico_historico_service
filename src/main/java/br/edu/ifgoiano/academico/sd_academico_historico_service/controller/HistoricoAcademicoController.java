package br.edu.ifgoiano.academico.sd_academico_historico_service.controller;

import br.edu.ifgoiano.academico.sd_academico_historico_service.entity.HistoricoAcademico;
import br.edu.ifgoiano.academico.sd_academico_historico_service.repository.HistoricoAcademicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciar histórico acadêmico.
 * Fornece endpoints para consultar eventos de histórico armazenados no banco de dados.
 */
@RestController
@RequestMapping("/historicos")
public class HistoricoAcademicoController {

    // Logger para rastreamento de requisições
    private static final Logger logger = LoggerFactory.getLogger(HistoricoAcademicoController.class);
    
    private final HistoricoAcademicoRepository historicoRepository;

    /**
     * Construtor com injeção de dependências
     */
    public HistoricoAcademicoController(HistoricoAcademicoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    /**
     * Retorna todos os históricos acadêmicos armazenados
     * @return lista de todos os históricos
     */
    @GetMapping
    public ResponseEntity<List<HistoricoAcademico>> listarTodosOsHistoricos() {
        logger.info("[HISTORICO-SERVICE] Listando todos os históricos acadêmicos");
        List<HistoricoAcademico> historicos = historicoRepository.findAll();
        return ResponseEntity.ok(historicos);
    }

    /**
     * Busca um histórico específico por ID
     * @param id identificador do histórico
     * @return histórico encontrado ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<HistoricoAcademico> buscarHistorico(@PathVariable Long id) {
        logger.info("[HISTORICO-SERVICE] Buscando histórico ID: {}", id);
        return historicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retorna todos os históricos de um aluno específico
     * @param alunoId identificador do aluno
     * @return lista de históricos do aluno
     */
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<HistoricoAcademico>> listarPorAluno(@PathVariable Long alunoId) {
        logger.info("[HISTORICO-SERVICE] Listando históricos do aluno: {}", alunoId);
        List<HistoricoAcademico> historicos = historicoRepository.findByAlunoId(alunoId);
        return ResponseEntity.ok(historicos);
    }

    /**
     * Retorna todos os históricos de um tipo específico
     * @param tipoEvento tipo do evento (MATRICULA_CRIADA, MATRICULA_CANCELADA)
     * @return lista de históricos do tipo especificado
     */
    @GetMapping("/tipo/{tipoEvento}")
    public ResponseEntity<List<HistoricoAcademico>> listarPorTipo(@PathVariable String tipoEvento) {
        logger.info("[HISTORICO-SERVICE] Listando históricos do tipo: {}", tipoEvento);
        List<HistoricoAcademico> historicos = historicoRepository.findByTipoEvento(tipoEvento);
        return ResponseEntity.ok(historicos);
    }
}



package br.edu.ifgoiano.academico.sd_academico_historico_service.controller;

import br.edu.ifgoiano.academico.sd_academico_historico_service.dto.HistoricoResponseDTO;
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
    public ResponseEntity<List<HistoricoResponseDTO>> listarTodosOsHistoricos() {
        logger.info("[HISTORICO-SERVICE] Listando todos os históricos acadêmicos");
        return ResponseEntity.ok(paraResponse(historicoRepository.findAll()));
    }

    /**
     * Busca um histórico específico por ID
     * @param id identificador do histórico
     * @return histórico encontrado ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<HistoricoResponseDTO> buscarHistorico(@PathVariable Long id) {
        logger.info("[HISTORICO-SERVICE] Buscando histórico ID: {}", id);
        return historicoRepository.findById(id)
                .map(this::paraResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retorna todos os históricos de um aluno específico
     * @param alunoId identificador do aluno
     * @return lista de históricos do aluno
     */
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<HistoricoResponseDTO>> listarPorAluno(@PathVariable Long alunoId) {
        logger.info("[HISTORICO-SERVICE] Listando históricos do aluno: {}", alunoId);
        return ResponseEntity.ok(paraResponse(historicoRepository.findByAlunoId(alunoId)));
    }

    /**
     * Retorna todos os históricos de um tipo específico
     * @param tipoEvento tipo do evento (MATRICULA_CRIADA, MATRICULA_CANCELADA)
     * @return lista de históricos do tipo especificado
     */
    @GetMapping("/tipo/{tipoEvento}")
    public ResponseEntity<List<HistoricoResponseDTO>> listarPorTipo(@PathVariable String tipoEvento) {
        logger.info("[HISTORICO-SERVICE] Listando históricos do tipo: {}", tipoEvento);
        return ResponseEntity.ok(paraResponse(historicoRepository.findByTipoEvento(tipoEvento)));
    }

    /**
     * Converte a entidade HistoricoAcademico no DTO de resposta exposto pela API.
     */
    private HistoricoResponseDTO paraResponse(HistoricoAcademico historico) {
        HistoricoResponseDTO response = new HistoricoResponseDTO();
        response.setId(historico.getId());
        response.setAlunoId(historico.getAlunoId());
        response.setTurmaId(historico.getTurmaId());
        response.setTipoEvento(historico.getTipoEvento());
        response.setDescricao(historico.getDescricao());
        response.setDataEvento(historico.getDataEvento());
        return response;
    }

    private List<HistoricoResponseDTO> paraResponse(List<HistoricoAcademico> historicos) {
        return historicos.stream().map(this::paraResponse).toList();
    }
}

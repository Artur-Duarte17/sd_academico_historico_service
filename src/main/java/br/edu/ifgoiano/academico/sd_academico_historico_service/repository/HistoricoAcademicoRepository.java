package br.edu.ifgoiano.academico.sd_academico_historico_service.repository;

import br.edu.ifgoiano.academico.sd_academico_historico_service.entity.HistoricoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para acesso aos dados de histórico acadêmico.
 * Fornece operações CRUD e consultas personalizadas para eventos.
 */
@Repository
public interface HistoricoAcademicoRepository extends JpaRepository<HistoricoAcademico, Long> {
    /**
     * Busca todos os eventos de histórico de um aluno específico
     * @param alunoId identificador do aluno
     * @return lista de históricos do aluno
     */
    List<HistoricoAcademico> findByAlunoId(Long alunoId);
    
    /**
     * Busca todos os eventos de um tipo específico
     * @param tipoEvento tipo do evento
     * @return lista de eventos do tipo especificado
     */
    List<HistoricoAcademico> findByTipoEvento(String tipoEvento);
}


package br.edu.ifgoiano.academico.sd_academico_historico_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade que representa um evento histórico acadêmico.
 * Registra todas as operações realizadas no fluxo de matrículas.
 */
@Entity
@Table(name = "historicos_academicos")
public class HistoricoAcademico {

    // Identificador único do histórico
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador do aluno relacionado ao evento
    @Column(nullable = false)
    private Long alunoId;

    // Identificador da turma relacionada ao evento
    @Column(nullable = false)
    private Long turmaId;

    // Tipo de evento (MATRICULA_CRIADA, MATRICULA_CANCELADA)
    @Column(nullable = false)
    private String tipoEvento;

    // Descrição detalhada do evento
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    // Data e hora do evento
    @Column(nullable = false)
    private LocalDateTime dataEvento;

    /**
     * Construtor padrão (necessário para JPA)
     */
    public HistoricoAcademico() {
    }

    /**
     * Construtor com parâmetros principais
     */
    public HistoricoAcademico(Long alunoId, Long turmaId, String tipoEvento, String descricao) {
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
        this.dataEvento = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    @Override
    public String toString() {
        return "HistoricoAcademico{" +
                "id=" + id +
                ", alunoId=" + alunoId +
                ", turmaId=" + turmaId +
                ", tipoEvento='" + tipoEvento + '\'' +
                ", dataEvento=" + dataEvento +
                '}';
    }
}


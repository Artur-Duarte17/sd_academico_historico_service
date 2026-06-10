package br.edu.ifgoiano.academico.sd_academico_historico_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Dados de saída ao expor um evento de histórico acadêmico pela API.
 */
@Getter
@Setter
@NoArgsConstructor
public class HistoricoResponseDTO {
    private Long id;
    private Long alunoId;
    private Long turmaId;
    private String tipoEvento;
    private String descricao;
    private LocalDateTime dataEvento;
}

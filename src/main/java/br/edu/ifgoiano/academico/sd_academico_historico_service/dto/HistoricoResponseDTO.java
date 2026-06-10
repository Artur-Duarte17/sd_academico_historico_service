package br.edu.ifgoiano.academico.sd_academico_historico_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representação de um evento de histórico acadêmico")
public class HistoricoResponseDTO {

    @Schema(description = "Identificador do registro de histórico", example = "1")
    private Long id;

    @Schema(description = "ID do aluno", example = "1")
    private Long alunoId;

    @Schema(description = "ID da turma", example = "1")
    private Long turmaId;

    @Schema(description = "Tipo do evento", example = "MATRICULA_CRIADA")
    private String tipoEvento;

    @Schema(description = "Descrição do evento", example = "Matrícula criada para o aluno 1 na turma 1.")
    private String descricao;

    @Schema(description = "Data/hora do evento", example = "2026-06-10T14:30:00")
    private LocalDateTime dataEvento;
}

package com.sistema.chamados.dto;

import java.time.LocalDateTime;

import com.sistema.chamados.model.Prioridade;
import com.sistema.chamados.model.StatusChamado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private StatusChamado status;
    private Prioridade prioridade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private Long solicitanteId;
    private String solicitanteNome;
    private Long atendenteId;
    private Long categoriaId;
    private String categoriaNome;
}
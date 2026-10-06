package com.sistema.chamados.dto;

import com.sistema.chamados.model.Prioridade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private Prioridade prioridade;

    @NotNull
    @Positive
    private Long solicitanteId;

    @NotNull
    @Positive
    private Long categoriaId;
}
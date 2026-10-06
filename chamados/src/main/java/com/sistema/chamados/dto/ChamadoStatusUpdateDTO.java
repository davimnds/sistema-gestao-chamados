package com.sistema.chamados.dto;

import com.sistema.chamados.model.StatusChamado;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoStatusUpdateDTO {

    @NotNull
    private StatusChamado status;
}
package com.sistema.chamados.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Long autorId;
    private String autorNome;
    private Long chamadoId;
}
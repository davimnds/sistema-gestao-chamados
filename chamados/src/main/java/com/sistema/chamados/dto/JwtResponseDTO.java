package com.sistema.chamados.dto;

import com.sistema.chamados.model.Perfil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDTO {

    private String token;
    private String tipo = "Bearer";
    private String email;
    private Perfil perfil;
}
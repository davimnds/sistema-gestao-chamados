package com.sistema.chamados.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.chamados.dto.ComentarioCreateDTO;
import com.sistema.chamados.dto.ComentarioDTO;
import com.sistema.chamados.service.ComentarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chamados/{chamadoId}/comentarios")
@SecurityRequirement(name = "bearerAuth")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public List<ComentarioDTO> listarPorChamado(@PathVariable Long chamadoId) {
        return comentarioService.listarPorChamado(chamadoId);
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> adicionar(
            @PathVariable Long chamadoId,
            @Valid @RequestBody ComentarioCreateDTO dados) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.adicionar(chamadoId, dados));
    }
}
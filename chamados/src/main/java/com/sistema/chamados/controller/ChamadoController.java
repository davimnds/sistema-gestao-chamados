package com.sistema.chamados.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.chamados.dto.ChamadoCreateDTO;
import com.sistema.chamados.dto.ChamadoDTO;
import com.sistema.chamados.dto.ChamadoStatusUpdateDTO;
import com.sistema.chamados.service.ChamadoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chamados")
@SecurityRequirement(name = "bearerAuth")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> criar(@Valid @RequestBody ChamadoCreateDTO dados) {
        ChamadoDTO criado = chamadoService.criar(dados);
        return ResponseEntity.created(URI.create("/api/chamados/" + criado.getId())).body(criado);
    }

    @GetMapping
    public List<ChamadoDTO> listarTodos() {
        return chamadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ChamadoDTO buscarPorId(@PathVariable Long id) {
        return chamadoService.buscarPorId(id);
    }

    @PatchMapping("/{id}/status")
    public ChamadoDTO atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody ChamadoStatusUpdateDTO dados) {
        return chamadoService.atualizarStatus(id, dados.getStatus());
    }
}
package com.sistema.chamados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.chamados.model.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
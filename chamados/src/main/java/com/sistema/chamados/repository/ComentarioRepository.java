package com.sistema.chamados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.chamados.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findAllByChamado_IdOrderByDataCriacaoAsc(Long chamadoId);
}
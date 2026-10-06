package com.sistema.chamados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.chamados.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
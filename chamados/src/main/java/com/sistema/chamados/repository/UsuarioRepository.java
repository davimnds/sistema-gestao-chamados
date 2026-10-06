package com.sistema.chamados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.sistema.chamados.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);

	Optional<Usuario> findByEmail(String email);
}
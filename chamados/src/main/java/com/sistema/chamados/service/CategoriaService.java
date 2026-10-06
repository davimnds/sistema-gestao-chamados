package com.sistema.chamados.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.chamados.dto.CategoriaCreateDTO;
import com.sistema.chamados.dto.CategoriaDTO;
import com.sistema.chamados.exception.ResourceNotFoundException;
import com.sistema.chamados.model.Categoria;
import com.sistema.chamados.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaDTO criar(CategoriaCreateDTO dados) {
        Categoria categoria = new Categoria();
        categoria.setNome(dados.getNome());
        return paraDTO(categoriaRepository.save(categoria));
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaService::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada: " + id));
    }

    private static CategoriaDTO paraDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }
}
package com.sistema.chamados.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.chamados.dto.ChamadoCreateDTO;
import com.sistema.chamados.dto.ChamadoDTO;
import com.sistema.chamados.exception.ResourceNotFoundException;
import com.sistema.chamados.model.Categoria;
import com.sistema.chamados.model.Chamado;
import com.sistema.chamados.model.StatusChamado;
import com.sistema.chamados.model.Usuario;
import com.sistema.chamados.repository.CategoriaRepository;
import com.sistema.chamados.repository.ChamadoRepository;
import com.sistema.chamados.repository.UsuarioRepository;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public ChamadoService(
            ChamadoRepository chamadoRepository,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository) {
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ChamadoDTO criar(ChamadoCreateDTO dados) {
        Usuario solicitante = usuarioRepository.findById(dados.getSolicitanteId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Solicitante não encontrado: " + dados.getSolicitanteId()));
        Categoria categoria = categoriaRepository.findById(dados.getCategoriaId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Categoria não encontrada: " + dados.getCategoriaId()));

        Chamado chamado = new Chamado();
        chamado.setTitulo(dados.getTitulo());
        chamado.setDescricao(dados.getDescricao());
        chamado.setPrioridade(dados.getPrioridade());
        chamado.setStatus(StatusChamado.ABERTO);
        chamado.setSolicitante(solicitante);
        chamado.setCategoria(categoria);

        return paraDTO(chamadoRepository.save(chamado));
    }

    @Transactional(readOnly = true)
    public List<ChamadoDTO> listarTodos() {
        return chamadoRepository.findAll().stream()
                .map(ChamadoService::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ChamadoDTO buscarPorId(Long id) {
        return paraDTO(buscarEntidade(id));
    }

    @Transactional
    public ChamadoDTO atualizarStatus(Long id, StatusChamado status) {
        Chamado chamado = buscarEntidade(id);
        chamado.setStatus(status);
        chamado.setDataAtualizacao(LocalDateTime.now());
        return paraDTO(chamadoRepository.saveAndFlush(chamado));
    }

    private Chamado buscarEntidade(Long id) {
        return chamadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado: " + id));
    }

    private static ChamadoDTO paraDTO(Chamado chamado) {
        return new ChamadoDTO(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getStatus(),
                chamado.getPrioridade(),
                chamado.getDataCriacao(),
                chamado.getDataAtualizacao(),
                chamado.getSolicitante().getId(),
                chamado.getSolicitante().getNome(),
                chamado.getAtendente() == null ? null : chamado.getAtendente().getId(),
                chamado.getCategoria().getId(),
                chamado.getCategoria().getNome());
    }
}
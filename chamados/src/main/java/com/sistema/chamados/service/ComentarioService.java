package com.sistema.chamados.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.chamados.dto.ComentarioCreateDTO;
import com.sistema.chamados.dto.ComentarioDTO;
import com.sistema.chamados.exception.ResourceNotFoundException;
import com.sistema.chamados.model.Chamado;
import com.sistema.chamados.model.Comentario;
import com.sistema.chamados.model.Usuario;
import com.sistema.chamados.repository.ChamadoRepository;
import com.sistema.chamados.repository.ComentarioRepository;
import com.sistema.chamados.repository.UsuarioRepository;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(
            ComentarioRepository comentarioRepository,
            ChamadoRepository chamadoRepository,
            UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ComentarioDTO adicionar(Long chamadoId, ComentarioCreateDTO dados) {
        Chamado chamado = buscarChamado(chamadoId);
        Usuario autor = usuarioRepository.findById(dados.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + dados.getAutorId()));

        Comentario comentario = new Comentario();
        comentario.setMensagem(dados.getMensagem());
        comentario.setChamado(chamado);
        comentario.setAutor(autor);
        return paraDTO(comentarioRepository.save(comentario));
    }

    @Transactional(readOnly = true)
    public List<ComentarioDTO> listarPorChamado(Long chamadoId) {
        buscarChamado(chamadoId);
        return comentarioRepository.findAllByChamado_IdOrderByDataCriacaoAsc(chamadoId).stream()
                .map(ComentarioService::paraDTO)
                .toList();
    }

    private Chamado buscarChamado(Long chamadoId) {
        return chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado: " + chamadoId));
    }

    private static ComentarioDTO paraDTO(Comentario comentario) {
        return new ComentarioDTO(
                comentario.getId(),
                comentario.getMensagem(),
                comentario.getDataCriacao(),
                comentario.getAutor().getId(),
                comentario.getAutor().getNome(),
                comentario.getChamado().getId());
    }
}
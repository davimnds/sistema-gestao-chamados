package com.sistema.chamados.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.chamados.model.Categoria;
import com.sistema.chamados.model.Chamado;
import com.sistema.chamados.model.Comentario;
import com.sistema.chamados.model.Perfil;
import com.sistema.chamados.model.Prioridade;
import com.sistema.chamados.model.StatusChamado;
import com.sistema.chamados.model.Usuario;
import com.sistema.chamados.repository.CategoriaRepository;
import com.sistema.chamados.repository.ChamadoRepository;
import com.sistema.chamados.repository.ComentarioRepository;
import com.sistema.chamados.repository.UsuarioRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ChamadoRepository chamadoRepository;
    private final ComentarioRepository comentarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            ChamadoRepository chamadoRepository,
            ComentarioRepository comentarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.chamadoRepository = chamadoRepository;
        this.comentarioRepository = comentarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            popularBanco();
        }
    }

    private void popularBanco() {
        Usuario admin = criarUsuario("Admin System", "admin@email.com", Perfil.ADMIN);
        Usuario atendente = criarUsuario("Atendente Silva", "atendente@email.com", Perfil.ATENDENTE);
        Usuario carlos = criarUsuario("Carlos Solicitante", "carlos@email.com", Perfil.SOLICITANTE);

        usuarioRepository.save(admin);
        usuarioRepository.save(atendente);
        usuarioRepository.save(carlos);

        Categoria suporteTi = criarCategoria("Suporte de TI");
        Categoria recursosHumanos = criarCategoria("Recursos Humanos");
        criarCategoria("Infraestrutura e Redes");

        Chamado problemaMonitor = criarChamado(
                "Problema no monitor",
                "Monitor com problema de funcionamento.",
                StatusChamado.ABERTO,
                Prioridade.ALTA,
                carlos,
                suporteTi,
                null);

        Chamado acessoRh = criarChamado(
                "Acesso ao sistema de RH",
                "Solicitação de acesso ao sistema de Recursos Humanos.",
                StatusChamado.EM_ANDAMENTO,
                Prioridade.MEDIA,
                carlos,
                recursosHumanos,
                atendente);

        chamadoRepository.save(problemaMonitor);
        chamadoRepository.save(acessoRh);

        Comentario comentario = new Comentario();
        comentario.setMensagem("Verificando as permissões do usuário.");
        comentario.setAutor(atendente);
        comentario.setChamado(acessoRh);
        comentarioRepository.save(comentario);
    }

    private Usuario criarUsuario(String nome, String email, Perfil perfil) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode("123456"));
        usuario.setPerfil(perfil);
        return usuario;
    }

    private Categoria criarCategoria(String nome) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        return categoriaRepository.save(categoria);
    }

    private Chamado criarChamado(
            String titulo,
            String descricao,
            StatusChamado status,
            Prioridade prioridade,
            Usuario solicitante,
            Categoria categoria,
            Usuario atendente) {
        Chamado chamado = new Chamado();
        chamado.setTitulo(titulo);
        chamado.setDescricao(descricao);
        chamado.setStatus(status);
        chamado.setPrioridade(prioridade);
        chamado.setSolicitante(solicitante);
        chamado.setCategoria(categoria);
        chamado.setAtendente(atendente);
        return chamado;
    }
}
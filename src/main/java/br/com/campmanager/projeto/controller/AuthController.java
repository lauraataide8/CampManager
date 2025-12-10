package br.com.campmanager.projeto.controller;

import br.com.campmanager.projeto.dto.auth.RegisterRequest;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.exception.BusinessException;
import br.com.campmanager.projeto.repository.UsuarioRepository;
import br.com.campmanager.projeto.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
final class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public Long registrar(RegisterRequest request) {
        if (usuarioRepository.existsByNickname(request.getNickname())) {
            throw new BusinessException("Nickname já está em uso.");
        }
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já está em uso.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeCompleto(request.getNome());
        novoUsuario.setNickname(request.getNickname());
        novoUsuario.setEmail(request.getEmail());

        novoUsuario.setSenha(passwordEncoder.encode(request.getSenha()));

        novoUsuario.setTipoUsuario("Comum");

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return usuarioSalvo.getIdUsuario();
    }

    public String autenticarEGerarToken(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Email ou senha inválidos."));

        if (!passwordEncoder.matches(senha, usuario.getSenhaHash())) {
            throw new BusinessException("Email ou senha inválidos.");
        }

        return jwtService.gerarToken(usuario);
    }
}
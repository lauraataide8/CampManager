package br.com.campmanager.projeto.service;

// IMPORTS CORRETOS (Sem Tomcat!)
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.campmanager.projeto.dto.LoginRequest;
import br.com.campmanager.projeto.dto.RegisterRequest;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.repositories.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository, 
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Usuario register(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado."); 
        }

        // Se der erro no findByNickname, adicione ele no Repository também (passo 3)
        // if (usuarioRepository.findByNickname(request.getNickname()).isPresent()) { ... }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(request.getEmail());
        novoUsuario.setNickname(request.getNickname());
        novoUsuario.setSenhaHash(encryptedPassword); 
        
        return usuarioRepository.save(novoUsuario);
    }

    public Authentication authenticate(LoginRequest request) {
        return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
    }
}
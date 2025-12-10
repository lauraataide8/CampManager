package br.com.campmanager.projeto.controller;


import br.com.campmanager.projeto.exception.BusinessException;
import br.com.campmanager.projeto.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint de Cadastro (Registro de novo usuário).
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody CadastroRequest request) {
        try {
            Long id = authService.registrar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso. ID: " + id);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint de Login (Autenticação e Geração de JWT).
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest request) {
        try {
            // 1. Autentica e gera o token no serviço
            String token = authService.autenticarEGerarToken(request.getEmail(), request.getSenha());
            
            // 2. Retorna o token no DTO de resposta
            return ResponseEntity.ok(new AuthResponse(token, "Login realizado com sucesso."));
            
        } catch (BusinessException e) {
            // Captura falhas de autenticação (email/senha inválidos)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, e.getMessage()));
        }
    }
}
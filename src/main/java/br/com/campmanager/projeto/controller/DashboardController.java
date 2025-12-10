package br.com.campmanager.projeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.campmanager.projeto.entity.Usuario;

/**
 * Endpoint de Teste para o Dashboard
 * Esta rota deve ser acessível APENAS se o usuário estiver AUTENTICADO (com JWT válido).
 */
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    // Rota protegida: /api/v1/dashboard/protected
    @GetMapping("/protected")
    public ResponseEntity<String> getProtectedData(Authentication authentication) {
        
        // Se a requisição chegou até aqui, o JWT foi validado com sucesso pelo SecurityFilter.
        String usuarioEmail = authentication.getName(); // Email do usuário extraído do token
        String nickname = "";
        
        // Você pode obter o nickname de forma mais robusta
        if (authentication.getPrincipal() instanceof Usuario usuario) {
             nickname = usuario.getNickname();
        }

        String message = String.format(
            "Bem-vindo ao Dashboard, %s! Seu email é %s. Você acessou uma rota protegida.", 
            nickname, 
            usuarioEmail
        );

        return ResponseEntity.ok(message);
    }
}
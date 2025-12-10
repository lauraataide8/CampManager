package br.com.campmanager.projeto.controller;

import br.com.campmanager.projeto.dto.ChangePasswordRequest;
import br.com.campmanager.projeto.dto.UsuarioResponse;
import br.com.campmanager.projeto.dto.UsuarioUpdateRequest;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.exception.BusinessException;
import br.com.campmanager.projeto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios") // Rota base: http://localhost:8080/api/v1/usuarios
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Helper para extrair o ID do usuário autenticado.
     */
    private Long getAuthenticatedUserId(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof Usuario usuario)) {
            // Este erro não deveria ocorrer se o filtro de segurança estiver funcionando
            throw new BusinessException("Sessão inválida ou expirada. Faça o login novamente.");
        }
        return usuario.getIdUsuario();
    }

    // ====================================================================
    // === 1. BUSCAR PERFIL (GET /perfil) =================================
    // ====================================================================

    /**
     * Retorna os dados do perfil do usuário autenticado.
     * Mapeia para GET /api/v1/usuarios/perfil
     */
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponse> buscarPerfil(Authentication authentication) {
        try {
            Long userId = getAuthenticatedUserId(authentication);
            Usuario usuario = usuarioService.buscarPerfil(userId);

            // Mapeia Entidade para DTO de Resposta
            UsuarioResponse response = UsuarioResponse.fromEntity(usuario);
            return ResponseEntity.ok(response);

        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }

    // ====================================================================
    // === 2. ATUALIZAR PERFIL (PUT /perfil) ==============================
    // ====================================================================

    /**
     * Atualiza os dados do perfil (Nickname, Nome Completo, Email) do usuário
     * autenticado.
     * Mapeia para PUT /api/v1/usuarios/perfil
     */
    @PutMapping("/perfil")
    public ResponseEntity<UsuarioResponse> atualizarPerfil(
            @Valid @RequestBody UsuarioUpdateRequest request,
            Authentication authentication) {
        try {
            Long userId = getAuthenticatedUserId(authentication);

            // Chama o Service para realizar a atualização e validações
            Usuario usuarioAtualizado = usuarioService.atualizarPerfil(userId, request);

            // Mapeia e retorna o perfil atualizado
            UsuarioResponse response = UsuarioResponse.fromEntity(usuarioAtualizado);
            return ResponseEntity.ok(response); // 200 OK

        } catch (BusinessException e) {
            // Erros de negócio (Email ou Nickname já em uso)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Retorna 400 Bad Request
        }
    }

    // ====================================================================
    // === 3. ALTERAR SENHA (PUT /senha) ==================================
    // ====================================================================

    /**
     * Altera a senha do usuário autenticado.
     * Mapeia para PUT /api/v1/usuarios/senha
     */
    @PutMapping("/senha")
    public ResponseEntity<?> alterarSenha(
            @Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication) {
        try {
            Long userId = getAuthenticatedUserId(authentication);

            usuarioService.alterarSenha(userId, request);

            // 200 OK, não precisa de corpo
            return ResponseEntity.ok().build();

        } catch (BusinessException e) {
            // Erro de senha atual incorreta ou validação
            // No caso de senha incorreta (401 Unauthorized), é mais seguro retornar 400
            // ou uma mensagem genérica, mas o Frontend espera 401 para 'Senha atual
            // incorreta'.
            if (e.getMessage().contains("incorreta")) {
                // Retorna 401 Unauthorized para o frontend tratar como senha incorreta.
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 400 Bad Request
        }
    }
}

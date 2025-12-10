package br.com.campmanager.projeto.controller;

import br.com.campmanager.projeto.dto.ConviteRequest;
import br.com.campmanager.projeto.dto.CriarEquipeRequest;
import br.com.campmanager.projeto.dto.EquipeDetalheResponse;
import br.com.campmanager.projeto.dto.EquipeResponse;
import br.com.campmanager.projeto.entity.Equipe;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.exception.BusinessException;
import br.com.campmanager.projeto.service.EquipeService;
import jakarta.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    /**
     * Helper: Obtém o ID do usuário autenticado a partir do objeto Authentication.
     */
    private Long getAuthenticatedUserId(Authentication authentication) {
        // Assume que o principal é um objeto Usuario ou algo que contenha o ID
        if (authentication == null || !(authentication.getPrincipal() instanceof Usuario usuario)) {
            // Lança exceção se a autenticação for inválida (deve ser tratada pelo Spring
            // Security)
            throw new BusinessException("Sessão inválida ou expirada.");
        }
        return usuario.getIdUsuario();
    }

    // ====================================================================
    // === 1. CRIAR EQUIPE (POST /criar) ==================================
    // ====================================================================

    @PostMapping("/criar")
    public ResponseEntity<EquipeResponse> criarEquipe(
            @Valid @RequestBody CriarEquipeRequest request,
            Authentication authentication) {
        try {
            Long capitaoId = getAuthenticatedUserId(authentication);
            Equipe novaEquipe = equipeService.criarEquipe(request, capitaoId);

            return ResponseEntity.status(HttpStatus.CREATED).body(EquipeResponse.fromEntity(novaEquipe));
        } catch (BusinessException e) {
            // Retorna 400 Bad Request em caso de erro de regra de negócio
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EquipeResponse(e.getMessage()));
        }
    }

    // ====================================================================
    // === 2. ENVIAR CONVITE (POST /convidar) =============================
    // ====================================================================

    @PostMapping("/convidar")
    public ResponseEntity<String> convidarMembro(
            @Valid @RequestBody ConviteRequest request,
            Authentication authentication) {
        try {
            Long capitaoId = getAuthenticatedUserId(authentication);

            equipeService.enviarConvite(capitaoId, request.getNicknameConvidado());

            return ResponseEntity.ok(
                    "Convite enviado com sucesso para " + request.getNicknameConvidado() + ". Ele precisa aceitar.");

        } catch (BusinessException e) {
            // Retorna 400 Bad Request em caso de erro de regra de negócio
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Logar o erro e retornar 500
            System.err.println("Erro inesperado ao convidar membro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar convite.");
        }
    }

    // ====================================================================
    // === 3. PERFIL DETALHADO DA EQUIPE (GET /perfil) ====================
    // ====================================================================

    @GetMapping("/perfil")
    public ResponseEntity<EquipeDetalheResponse> buscarPerfilEquipe(Authentication authentication) {
        Long usuarioId = getAuthenticatedUserId(authentication);

        Optional<Equipe> equipeOpt = equipeService.buscarPerfilEquipePorUsuarioId(usuarioId);

        if (equipeOpt.isPresent()) {
            EquipeDetalheResponse response = EquipeDetalheResponse.fromEntity(equipeOpt.get());
            return ResponseEntity.ok(response);
        } else {
            // Retorna 404 Not Found se o usuário não estiver em nenhuma equipe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // ====================================================================
    // === 4. ACEITAR CONVITE (POST /aceitar-convite) =====================
    // ====================================================================

    @PostMapping("/aceitar-convite")
    public ResponseEntity<String> aceitarConvite(Authentication authentication) {
        try {
            Long usuarioId = getAuthenticatedUserId(authentication);
            Equipe equipeAceita = equipeService.aceitarConvite(usuarioId);

            return ResponseEntity
                    .ok("Convite aceito! Você agora é membro oficial da equipe " + equipeAceita.getNomeEquipe() + ".");
        } catch (BusinessException e) {
            // Retorna 400 Bad Request em caso de erro de regra de negócio
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Logar o erro e retornar 500
            System.err.println("Erro inesperado ao aceitar convite: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao aceitar convite.");
        }
    }

    // --- Futuras funcionalidades: DELETE /remover-membro, PUT /atualizar ---
}
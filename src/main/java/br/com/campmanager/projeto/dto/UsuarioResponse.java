package br.com.campmanager.projeto.dto;

import br.com.campmanager.projeto.entity.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO utilizado para retornar dados do usuário para o Frontend (ex: tela de
 * Minha Conta).
 * Evita expor o senhaHash.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long idUsuario;
    private String nickname;
    private String nomeCompleto;
    private String email;
    private String discordId;
    private String googleId;
    private String tipoUsuario; // Mapeia o Enum TipoUsuario
    private LocalDateTime dataCriacao;
    private String equipeAtual; // Nome da equipe ou null

    /**
     * Construtor estático para converter a Entidade Usuario para este DTO.
     * 
     * @param usuario A entidade Usuario completa.
     * @return O objeto DTO formatado.
     */
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .nickname(usuario.getNickname())
                .nomeCompleto(usuario.getNomeCompleto())
                .email(usuario.getEmail())
                .discordId(usuario.getDiscordId())
                .googleId(usuario.getGoogleId())
                .tipoUsuario(usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().name() : "MEMBER")
                .dataCriacao(usuario.getDataCriacao())
                // Verifica se o usuário tem equipe para evitar NullPointerException
                .equipeAtual(usuario.getEquipe() != null ? usuario.getEquipe().getNomeEquipe() : null)
                .build();
    }
}
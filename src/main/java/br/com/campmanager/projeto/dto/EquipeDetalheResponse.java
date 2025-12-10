package br.com.campmanager.projeto.dto;

import br.com.campmanager.projeto.entity.Equipe;
import br.com.campmanager.projeto.entity.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDetalheResponse {

    private Long idEquipe;
    private String nomeEquipe;
    private String tagGuilda;
    private String capitaoNickname;
    private String dataCriacao;
    private Set<MembroEquipeDTO> membros;

    // DTO interno para simplificar a lista de membros
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MembroEquipeDTO {
        private Long idUsuario;
        private String nickname;
        private String email;
        private String tipoUsuario;
        private boolean isCapitao;

        public static MembroEquipeDTO fromEntity(Usuario usuario, Long capitaoId) {
            return MembroEquipeDTO.builder()
                    .idUsuario(usuario.getIdUsuario())
                    .nickname(usuario.getNickname())
                    .email(usuario.getEmail())
                    .tipoUsuario(usuario.getTipoUsuario().name())
                    .isCapitao(usuario.getIdUsuario().equals(capitaoId))
                    .build();
        }

    }

    /**
     * Construtor estático para mapear a Entidade Equipe para este DTO.
     */
    public static EquipeDetalheResponse fromEntity(Equipe equipe) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Mapeia os membros (incluindo o Capitão)
        Set<MembroEquipeDTO> membrosList = equipe.getMembros().stream()
                .map(membro -> MembroEquipeDTO.fromEntity(membro, equipe.getCapitao().getIdUsuario()))
                .collect(Collectors.toSet());

        return EquipeDetalheResponse.builder()
                .idEquipe(equipe.getIdEquipe())
                .nomeEquipe(equipe.getNomeEquipe())
                .tagGuilda(equipe.getTagGuilda())
                .capitaoNickname(equipe.getCapitao().getNickname())
                .dataCriacao(equipe.getDataCriacao().format(formatter))
                .membros(membrosList)
                .build();
    }
}


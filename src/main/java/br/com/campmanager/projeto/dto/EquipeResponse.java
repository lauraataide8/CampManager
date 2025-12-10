package br.com.campmanager.projeto.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder; // Adicionando Builder para facilitar a criação do objeto
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.campmanager.projeto.entity.Equipe;

/**
 * DTO utilizado para retornar os dados da Equipe recém-criada ao frontend.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipeResponse {

    private Long idEquipe;
    private String nomeEquipe;
    private String tagGuilda;
    private String capitaoNickname; // Retorna apenas o nickname do capitão, não a Entidade Usuario completa
    private LocalDateTime dataCriacao;
    private String status;
    private int numeroMembros; // Retorna o número de membros (inicialmente 1: o Capitão)

    /**
     * Método estático para converter a Entidade Equipe para este DTO de Resposta.
     * 
     * @param equipe A entidade Equipe recém-salva.
     * @return O objeto DTO formatado.
     */
    public static EquipeResponse fromEntity(Equipe equipe) {
        // Garantimos que a lista de membros não seja nula antes de contar
        int membrosCount = (equipe.getMembros() != null) ? equipe.getMembros().size() : 0;

        // Se a lista de membros foi carregada, use o count. Caso contrário, assume 1 (o
        // capitão).
        // Na criação, o Hibernate pode não carregar a lista de imediato, então
        // garantimos 1.
        if (membrosCount == 0 && equipe.getCapitao() != null) {
            membrosCount = 1;
        }

        return EquipeResponse.builder()
                .idEquipe(equipe.getIdEquipe())
                .nomeEquipe(equipe.getNomeEquipe())
                .tagGuilda(equipe.getTagGuilda())
                .capitaoNickname(equipe.getCapitao().getNickname())
                .dataCriacao(equipe.getDataCriacao())
                .status(equipe.getStatus())
                .numeroMembros(membrosCount)
                .build();
    }
}
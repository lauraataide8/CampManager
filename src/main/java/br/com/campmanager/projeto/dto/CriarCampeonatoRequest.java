package br.com.campmanager.projeto.dto;

import java.time.LocalDate;

import br.com.campmanager.projeto.enums.FormatoCampeonato;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarCampeonatoRequest {

    @NotBlank(message = "O nome do campeonato é obrigatório.")
    private String nome;

    @NotNull(message = "O formato do campeonato é obrigatório.")
    private FormatoCampeonato formato;

    @Min(value = 2, message = "O número máximo de equipes deve ser pelo menos 2.")
    @NotNull(message = "O número máximo de equipes é obrigatório.")
    private Integer maxEquipes;

    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início não pode ser anterior à data atual.")
    private LocalDate dataInicio;

    // Opcional
    private LocalDate dataFim;
}

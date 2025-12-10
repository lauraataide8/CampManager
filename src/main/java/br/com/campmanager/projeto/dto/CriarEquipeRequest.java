package br.com.campmanager.projeto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO para receber os dados necessários para a criação de uma nova Equipe.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarEquipeRequest {

    // Nome da equipe: Obrigatório, tamanho limitado, e deve ser único no sistema
    // (validaremos isso no Service).
    @NotBlank(message = "O nome da equipe é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome da equipe deve ter entre 3 e 100 caracteres.")
    private String nomeEquipe;

    // Tag da guilda: Obrigatória, mais curta, e deve ser única.
    @NotBlank(message = "A TAG da guilda é obrigatória.")
    @Size(min = 2, max = 10, message = "A TAG deve ter entre 2 e 10 caracteres.")
    private String tagGuilda;

	public String getNomeEquipe() {
		// TODO Auto-generated method stub
		return null;
	}

    // O ID do Capitão: É opcional aqui se você estiver pegando o ID do usuário
    // autenticado.
    // No entanto, para fins de teste ou se for um formulário onde o capitão é
    // selecionado,
    // podemos mantê-lo, mas o mais seguro é pegar o ID do JWT (faremos isso no
    // Controller).
    // Por enquanto, vamos assumir que o sistema pegará o ID do usuário autenticado
    // no Controller.
}
package br.com.campmanager.projeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para receber a requisição de atualização de dados do perfil (Nome,
 * Nickname, Email).
 */
@Getter
@Setter
public class UsuarioUpdateRequest {

    @NotBlank(message = "O nickname é obrigatório.")
    @Size(min = 3, max = 50, message = "O nickname deve ter entre 3 e 50 caracteres.")
    private String nickname;

    // Não é obrigatório, mas o campo é opcional no Frontend
    private String nomeCompleto;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O formato do email é inválido.")
    private String email;

    // Não incluir senha neste DTO!
}
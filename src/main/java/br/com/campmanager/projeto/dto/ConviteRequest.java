package br.com.campmanager.projeto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConviteRequest {

    @NotBlank(message = "O nickname do usuário convidado é obrigatório.")
    @Size(min = 3, max = 50, message = "O nickname deve ter entre 3 e 50 caracteres.")
    private String nicknameConvidado;
}
package br.com.campmanager.projeto.dto.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO para receber os dados do formulário "Fale Conosco" do frontend.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContatoRequest {

    // Nome é obrigatório
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    // Email é obrigatório e deve ter formato válido
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres.")
    private String email;

    // Telefone é opcional
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
    private String telefone;

    // Mensagem é obrigatória
    @NotBlank(message = "A mensagem não pode estar vazia.")
    @Size(max = 1000, message = "A mensagem deve ter no máximo 1000 caracteres.")
    private String mensagem;


}
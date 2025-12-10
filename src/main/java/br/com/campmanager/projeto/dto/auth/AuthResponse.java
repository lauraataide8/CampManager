package br.com.campmanager.projeto.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    // O token JWT gerado após o login
    private String token;

    // Campo opcional para retornar mensagens ou status
    private String mensagem;
}
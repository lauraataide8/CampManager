package br.com.campmanager.projeto.dto;

import lombok.Data;

@Data // O Lombok gera automaticamente os Getters, Setters, toString, etc.
public class LoginRequest {
    
    private String email;
    private String password;
    
	public LoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
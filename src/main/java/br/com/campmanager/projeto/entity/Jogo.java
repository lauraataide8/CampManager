package br.com.campmanager.projeto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogo;

    private String nome;
    private String desenvolvedora;
    private String modoPadrao;
    
	public Jogo(Long idJogo, String nome, String desenvolvedora, String modoPadrao) {
		this.idJogo = idJogo;
		this.nome = nome;
		this.desenvolvedora = desenvolvedora;
		this.modoPadrao = modoPadrao;
	}

	public Long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public String getModoPadrao() {
		return modoPadrao;
	}

	public void setModoPadrao(String modoPadrao) {
		this.modoPadrao = modoPadrao;
	}
	
}

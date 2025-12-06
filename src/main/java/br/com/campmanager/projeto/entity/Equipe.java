package br.com.campmanager.projeto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String nomeEquipe;
    private String tagGuilda;

    @ManyToOne
    @JoinColumn(name = "id_capitao")
    private Usuario capitao;

	public Equipe(Long idEquipe, String nomeEquipe, String tagGuilda, Usuario capitao) {
		this.idEquipe = idEquipe;
		this.nomeEquipe = nomeEquipe;
		this.tagGuilda = tagGuilda;
		this.capitao = capitao;
	}

	public Long getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(Long idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public String getTagGuilda() {
		return tagGuilda;
	}

	public void setTagGuilda(String tagGuilda) {
		this.tagGuilda = tagGuilda;
	}

	public Usuario getCapitao() {
		return capitao;
	}

	public void setCapitao(Usuario capitao) {
		this.capitao = capitao;
	}
	
	
    
    
}

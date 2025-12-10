package br.com.campmanager.projeto.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString; // Importante para evitar o loop infinito

@Data // Gera Getters, Setters, toString, hashCode, equals
@NoArgsConstructor // Construtor sem argumentos (necessário para JPA)
@AllArgsConstructor // Construtor com todos os argumentos
@Entity
@Table(name = "equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    // Nome da equipe (Único e Obrigatório)
    @Column(unique = true, nullable = false, length = 100)
    private String nomeEquipe;

    // Tag ou abreviação da guilda (Único e Obrigatório)
    @Column(unique = true, nullable = false, length = 10)
    private String tagGuilda;

    // -------------------------------------------------------------
    // RELACIONAMENTO 1: CAPITÃO (ManyToOne)
    // Uma equipe tem um capitão. Um Capitão é um Usuário.
    @ManyToOne
    @JoinColumn(name = "id_capitao", nullable = false)
    private Usuario capitao;
    // -------------------------------------------------------------

    // -------------------------------------------------------------
    // RELACIONAMENTO 2: MEMBROS (OneToMany)
    // Uma equipe tem vários membros (Usuários).
    // O @ToString.Exclude impede que o Java entre em loop infinito ao imprimir a equipe
    @OneToMany(mappedBy = "equipe")
    @ToString.Exclude 
    private List<Usuario> membros;
    // -------------------------------------------------------------

    // Data de criação (Campo automático, não atualizável)
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    // Status padrão
    @Column(length = 50, nullable = false)
    private String status = "ATIVA";

	public Equipe(Long idEquipe, String nomeEquipe, String tagGuilda, Usuario capitao, List<Usuario> membros,
			LocalDateTime dataCriacao, String status) {
		this.idEquipe = idEquipe;
		this.nomeEquipe = nomeEquipe;
		this.tagGuilda = tagGuilda;
		this.capitao = capitao;
		this.membros = membros;
		this.dataCriacao = dataCriacao;
		this.status = status;
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

	public List<Usuario> getMembros() {
		return membros;
	}

	public void setMembros(List<Usuario> membros) {
		this.membros = membros;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    

}
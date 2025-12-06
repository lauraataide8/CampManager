package br.com.campmanager.projeto.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import br.com.campmanager.projeto.enums.StatusCampeonato;

@Data
@Entity
@Table(name = "campeonatos")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampeonato;

    private String titulo;
    private String regras;
    private LocalDate dataInicio;

    @Enumerated(EnumType.STRING)
    private StatusCampeonato status;

    @ManyToOne
    @JoinColumn(name = "id_organizador")
    private Usuario organizador;

    @ManyToOne
    @JoinColumn(name = "id_jogo")
    private Jogo jogo;
    
    @ManyToMany
    @JoinTable(
      name = "inscricoes", 
      joinColumns = @JoinColumn(name = "id_campeonato"), 
      inverseJoinColumns = @JoinColumn(name = "id_equipe"))
    private List<Equipe> equipesInscritas;

	public Campeonato(Long idCampeonato, String titulo, String regras, LocalDate dataInicio, StatusCampeonato status,
			Usuario organizador, Jogo jogo, List<Equipe> equipesInscritas) {
		this.idCampeonato = idCampeonato;
		this.titulo = titulo;
		this.regras = regras;
		this.dataInicio = dataInicio;
		this.status = status;
		this.organizador = organizador;
		this.jogo = jogo;
		this.equipesInscritas = equipesInscritas;
	}

	public Long getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Long idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRegras() {
		return regras;
	}

	public void setRegras(String regras) {
		this.regras = regras;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public StatusCampeonato getStatus() {
		return status;
	}

	public void setStatus(StatusCampeonato status) {
		this.status = status;
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Usuario organizador) {
		this.organizador = organizador;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Equipe> getEquipesInscritas() {
		return equipesInscritas;
	}

	public void setEquipesInscritas(List<Equipe> equipesInscritas) {
		this.equipesInscritas = equipesInscritas;
	}
    
    
}

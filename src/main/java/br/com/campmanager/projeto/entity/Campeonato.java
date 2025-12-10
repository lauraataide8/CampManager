package br.com.campmanager.projeto.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.campmanager.projeto.enums.FormatoCampeonato;
import br.com.campmanager.projeto.enums.StatusCampeonato;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campeonatos")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampeonato;

    @Column(nullable = false, length = 100, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCampeonato status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatoCampeonato formato;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @Column(nullable = false)
    private Integer maxEquipes;

    // Campo para armazenar o chaveamento em formato JSON (ex: estrutura da chave de eliminação)
    @Lob 
    private String chaveamentoJson;

    // Relacionamento: Quem criou o campeonato
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_criador_id", nullable = false)
    private Usuario criador;

    // Relacionamento: Equipes participantes (Tabela de junção: campeonato_equipe)
    @ManyToMany
    @JoinTable(
        name = "campeonato_equipe",
        joinColumns = @JoinColumn(name = "campeonato_id"),
        inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    private Set<Equipe> equipesParticipantes = new HashSet<>();

	public Campeonato(Long idCampeonato, String nome, StatusCampeonato status, FormatoCampeonato formato,
			LocalDate dataInicio, LocalDate dataFim, Integer maxEquipes, String chaveamentoJson, Usuario criador,
			Set<Equipe> equipesParticipantes) {
		this.idCampeonato = idCampeonato;
		this.nome = nome;
		this.status = status;
		this.formato = formato;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.maxEquipes = maxEquipes;
		this.chaveamentoJson = chaveamentoJson;
		this.criador = criador;
		this.equipesParticipantes = equipesParticipantes;
	}

	public Long getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Long idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusCampeonato getStatus() {
		return status;
	}

	public void setStatus(StatusCampeonato status) {
		this.status = status;
	}

	public FormatoCampeonato getFormato() {
		return formato;
	}

	public void setFormato(FormatoCampeonato formato) {
		this.formato = formato;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getMaxEquipes() {
		return maxEquipes;
	}

	public void setMaxEquipes(Integer maxEquipes) {
		this.maxEquipes = maxEquipes;
	}

	public String getChaveamentoJson() {
		return chaveamentoJson;
	}

	public void setChaveamentoJson(String chaveamentoJson) {
		this.chaveamentoJson = chaveamentoJson;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Set<Equipe> getEquipesParticipantes() {
		return equipesParticipantes;
	}

	public void setEquipesParticipantes(Set<Equipe> equipesParticipantes) {
		this.equipesParticipantes = equipesParticipantes;
	}
	
	
    
    
}


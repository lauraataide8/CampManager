package br.com.campmanager.projeto.entity;

import br.com.campmanager.projeto.enums.StatusPartida;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartida;

    private Integer faseChave;

    @ManyToOne
    @JoinColumn(name = "id_equipe_a")
    private Equipe equipeA;

    @ManyToOne
    @JoinColumn(name = "id_equipe_b")
    private Equipe equipeB;

    @Column(name = "pontuacao_equipe_a")
    private Integer pontuacaoEquipeA;

    @Column(name = "pontuacao_equipe_b")
    private Integer pontuacaoEquipeB;

    @ManyToOne
    @JoinColumn(name = "id_vencedor")
    private Equipe vencedor;

    private String linkPrintResultado;

    @Enumerated(EnumType.STRING)
    private StatusPartida status;
    
    @ManyToOne
    @JoinColumn(name = "id_campeonato")
    private Campeonato campeonato;

	public Partida(Long idPartida, Integer faseChave, Equipe equipeA, Equipe equipeB, Integer pontuacaoEquipeA,
			Integer pontuacaoEquipeB, Equipe vencedor, String linkPrintResultado, StatusPartida status,
			Campeonato campeonato) {
		this.idPartida = idPartida;
		this.faseChave = faseChave;
		this.equipeA = equipeA;
		this.equipeB = equipeB;
		this.pontuacaoEquipeA = pontuacaoEquipeA;
		this.pontuacaoEquipeB = pontuacaoEquipeB;
		this.vencedor = vencedor;
		this.linkPrintResultado = linkPrintResultado;
		this.status = status;
		this.campeonato = campeonato;
	}

	public Long getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	public Integer getFaseChave() {
		return faseChave;
	}

	public void setFaseChave(Integer faseChave) {
		this.faseChave = faseChave;
	}

	public Equipe getEquipeA() {
		return equipeA;
	}

	public void setEquipeA(Equipe equipeA) {
		this.equipeA = equipeA;
	}

	public Equipe getEquipeB() {
		return equipeB;
	}

	public void setEquipeB(Equipe equipeB) {
		this.equipeB = equipeB;
	}

	public Integer getPontuacaoEquipeA() {
		return pontuacaoEquipeA;
	}

	public void setPontuacaoEquipeA(Integer pontuacaoEquipeA) {
		this.pontuacaoEquipeA = pontuacaoEquipeA;
	}

	public Integer getPontuacaoEquipeB() {
		return pontuacaoEquipeB;
	}

	public void setPontuacaoEquipeB(Integer pontuacaoEquipeB) {
		this.pontuacaoEquipeB = pontuacaoEquipeB;
	}

	public Equipe getVencedor() {
		return vencedor;
	}

	public void setVencedor(Equipe vencedor) {
		this.vencedor = vencedor;
	}

	public String getLinkPrintResultado() {
		return linkPrintResultado;
	}

	public void setLinkPrintResultado(String linkPrintResultado) {
		this.linkPrintResultado = linkPrintResultado;
	}

	public StatusPartida getStatus() {
		return status;
	}

	public void setStatus(StatusPartida status) {
		this.status = status;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	
    
    
}

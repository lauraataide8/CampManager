package br.com.campmanager.projeto.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.campmanager.projeto.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera Getters, Setters, toString, hashCode, equals
@NoArgsConstructor // Construtor sem argumentos (necessário para JPA)
@AllArgsConstructor // Construtor com todos os argumentos
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nickname_jogo", nullable = false)
    private String nickname;

    @Column(name = "game_id_externo")
    private String gameIdExterno;

    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String email;

    // Coluna para a senha criptografada
    private String senhaHash;

    private String discordId;
    private String googleId;

    

	@Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    // -----------------------------------------------------------------
    // NOVO CAMPO: RELACIONAMENTO COM A EQUIPE
    // Um Usuário pertence a UMA Equipe (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "equipe_id") // Chave estrangeira na tabela 'usuarios'
    private Equipe equipe;
    // -----------------------------------------------------------------

    // Métodos da interface UserDetails (Obrigatório para Spring Security)

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mapeia o Enum TipoUsuario para a permissão do Spring Security
        // Se for ADMIN, dá a permissão ADMIN. Caso contrário, MEMBER (ou PLAYER, etc.)
        if (this.tipoUsuario == TipoUsuario.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));
        }
    }

    public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(Long idUsuario, String nickname, String gameIdExterno, String nomeCompleto, String email,
			String senhaHash, String discordId, String googleId, TipoUsuario tipoUsuario, LocalDateTime dataCriacao,
			Equipe equipe) {
		super();
		this.idUsuario = idUsuario;
		this.nickname = nickname;
		this.gameIdExterno = gameIdExterno;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senhaHash = senhaHash;
		this.discordId = discordId;
		this.googleId = googleId;
		this.tipoUsuario = tipoUsuario;
		this.dataCriacao = dataCriacao;
		this.equipe = equipe;
	}

	@Override
    public String getPassword() {
        return this.senhaHash;
    }

    @Override
    public String getUsername() {
        // Usamos o email como nome de usuário para o login
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGameIdExterno() {
		return gameIdExterno;
	}

	public void setGameIdExterno(String gameIdExterno) {
		this.gameIdExterno = gameIdExterno;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaHash() {
		return senhaHash;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public String getDiscordId() {
		return discordId;
	}

	public void setDiscordId(String discordId) {
		this.discordId = discordId;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
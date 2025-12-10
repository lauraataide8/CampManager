package br.com.campmanager.projeto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensagens_contato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensagemContato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false, length = 1000)
    private String mensagem;

    // Campo gerado automaticamente na persistência
    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio = LocalDateTime.now();

    // Status para rastreamento (ex: PENDENTE, EM_ANDAMENTO, RESOLVIDO)
    @Column(name = "status", length = 50, nullable = false)
    private String status = "PENDENTE"; 
}

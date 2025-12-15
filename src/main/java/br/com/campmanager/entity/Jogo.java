package br.com.campmanager.entity;

import jakarta.persistence.*;
import lombok.Data; // Cria Getters, Setters, etc automaticamente

@Data // Essa anotação do Lombok é essencial para não precisarmos escrever getters/setters
@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Definido como Long

    private String timeCasa;
    private String timeVisitante;
    private Integer placarCasa;
    private Integer placarVisitante;
}
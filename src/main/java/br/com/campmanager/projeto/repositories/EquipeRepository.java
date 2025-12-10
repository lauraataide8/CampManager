package br.com.campmanager.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campmanager.projeto.entity.Equipe;

import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    // Método para buscar a equipe pelo nome (para garantir unicidade no cadastro)
    Optional<Equipe> findByNome(String nome);

    // Método para buscar a equipe pela tag (também deve ser única)
    Optional<Equipe> findByTag(String tag);
}
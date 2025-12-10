package br.com.campmanager.projeto.repositories;


import br.com.campmanager.projeto.entity.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

    // Método para verificar se já existe um campeonato com o mesmo nome
    Optional<Campeonato> findByNomeIgnoreCase(String nome);

    // Você pode adicionar consultas personalizadas aqui, se necessário no futuro:
    // Ex: List<Campeonato> findAllByStatus(StatusCampeonato status);
    
}

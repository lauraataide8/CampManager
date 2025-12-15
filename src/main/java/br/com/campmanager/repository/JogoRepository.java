package br.com.campmanager.repository;

import br.com.campmanager.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    // Note o <Jogo, Long>. Se estiver Integer aqui, dá erro.
}
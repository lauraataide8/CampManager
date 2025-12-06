package br.com.campmanager.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campmanager.projeto.entity.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
}

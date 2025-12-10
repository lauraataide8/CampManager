package br.com.campmanager.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campmanager.projeto.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Adicione estas duas linhas dentro da interface:
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNickname(String nickname);
}
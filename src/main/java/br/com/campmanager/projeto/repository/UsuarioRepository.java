package br.com.campmanager.projeto.repository;

import br.com.campmanager.projeto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo email. Essencial para o processo de autenticação (UserDetailsService).
     * @param email O email do usuário
     * @return Um Optional contendo o Usuario, se encontrado.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica se já existe um usuário com o nickname fornecido.
     * @param nickname O nickname a ser verificado
     * @return true se o nickname já existe no banco.
     */
    boolean existsByNickname(String nickname);

    /**
     * Verifica se já existe um usuário com o email fornecido.
     * @param email O email a ser verificado
     * @return true se o email já existe no banco.
     */
    boolean existsByEmail(String email);
}
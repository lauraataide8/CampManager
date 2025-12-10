package br.com.campmanager.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campmanager.projeto.entity.MensagemContato;

/**
 * Repository para a entidade MensagemContato.
 * Estende JpaRepository para herdar automaticamente as operações CRUD básicas
 * (salvar, buscar por ID, listar tudo, deletar).
 *
 * Parâmetros:
 * - MensagemContato: A entidade JPA que este repositório gerencia.
 * - Long: O tipo da chave primária (ID) da entidade.
 */
@Repository
public interface MensagemContatoRepository extends JpaRepository<MensagemContato, Long> {

    // Nenhuma implementação é necessária aqui. O Spring Boot cuida de tudo.
    // Você pode adicionar métodos personalizados aqui, como:
    // List<MensagemContato> findByStatus(String status);
}
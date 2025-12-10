package br.com.campmanager.projeto.repository;

import br.com.campmanager.projeto.entity.MensagemContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MensagemContatoRepository extends JpaRepository<MensagemContato, Long> {

}
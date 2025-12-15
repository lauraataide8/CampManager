package br.com.campmanager.controller;

import br.com.campmanager.entity.Jogo;
import br.com.campmanager.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;

    // 1. Listar todos
    @GetMapping
    public List<Jogo> listarTodos() {
        return jogoRepository.findAll();
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {
        return jogoRepository.findById(id)
                .map(jogo -> ResponseEntity.ok().body(jogo))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Criar jogo
    @PostMapping
    public Jogo criarJogo(@RequestBody Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    // 4. Atualizar jogo
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoDetalhes) {
        return jogoRepository.findById(id)
                .map(jogoExistente -> {
                    // Atualiza os dados com o que veio no corpo da requisição
                    jogoExistente.setTimeCasa(jogoDetalhes.getTimeCasa());
                    jogoExistente.setTimeVisitante(jogoDetalhes.getTimeVisitante());
                    jogoExistente.setPlacarCasa(jogoDetalhes.getPlacarCasa());
                    jogoExistente.setPlacarVisitante(jogoDetalhes.getPlacarVisitante());

                    Jogo atualizado = jogoRepository.save(jogoExistente);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Deletar jogo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Long id) {
        if (jogoRepository.existsById(id)) {
            jogoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
package br.com.campmanager.projeto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.campmanager.projeto.entity.Jogo;
import br.com.campmanager.projeto.repositories.JogoRepository;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoRepository repository;

    @GetMapping
    public List<Jogo> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Jogo criar(@RequestBody Jogo jogo) {
        return repository.save(jogo);
    }
}
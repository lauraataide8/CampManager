package br.com.campmanager.projeto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.campmanager.projeto.entity.Campeonato;
import br.com.campmanager.projeto.repositories.CampeonatoRepository;

import java.util.List;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoRepository repository;

    @GetMapping
    public List<Campeonato> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Campeonato criar(@RequestBody Campeonato campeonato) {
        return repository.save(campeonato);
    }
}
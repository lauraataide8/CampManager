package br.com.campmanager.projeto.controller;

import br.com.campmanager.projeto.entity.Jogo;
import br.com.campmanager.projeto.enums.StatusCampeonato; // Import do Enum
import br.com.campmanager.projeto.repositories.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Import correto para web
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // Mudou de RestController para Controller
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoRepository repository;

    // 1. Listar (GET /jogos)
    @GetMapping
    public String listar(Model model) {
        // Manda a lista de jogos para o HTML "index.html"
        model.addAttribute("listaJogos", repository.findAll());
        return "jogos/index"; 
    }

    // 2. Abrir Formulário de Novo Jogo (GET /jogos/novo)
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("jogo", new Jogo());
        // Envia os Status (Enum) para preencher o <select> no HTML
        model.addAttribute("listaStatus", StatusCampeonato.values());
        return "jogos/form";
    }

    // 3. Salvar o Jogo (POST /jogos/salvar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Jogo jogo) {
        // @ModelAttribute pega os dados do form HTML
        repository.save(jogo);
        return "redirect:/jogos"; // Recarrega a página da lista
    }

    // 4. Excluir (GET /jogos/deletar/{id})
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/jogos";
    }
    
    // 5. Editar (GET /jogos/editar/{id})
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Jogo jogo = repository.findById(id).orElse(null);
        
        if(jogo != null){
            model.addAttribute("jogo", jogo);
            model.addAttribute("listaStatus", StatusCampeonato.values());
            return "jogos/form";
        }
        return "redirect:/jogos";
    }
}

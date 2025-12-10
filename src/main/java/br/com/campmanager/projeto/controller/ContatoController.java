package br.com.campmanager.projeto.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contato") // Rota base: http://localhost:8080/api/v1/contato
public class ContatoController {

    private final ContatoService contatoService;

    // Injeção de dependência do Serviço
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /**
     * Endpoint para receber a mensagem do formulário "Fale Conosco".
     * Mapeia para POST /api/v1/contato
     * * @param request O DTO ContatoRequest com os dados do frontend.
     * 
     * @return 201 Created se a mensagem for salva com sucesso.
     */
    @PostMapping
    public ResponseEntity<?> receberMensagem(@Valid @RequestBody ContatoRequest request) {

        try {
            // O @Valid garante que as anotações (@NotBlank, @Email) no DTO
            // sejam verificadas antes de chegar aqui. Se falhar, retorna 400 Bad Request.

            // Chama o serviço para mapear e salvar a mensagem no banco
            MensagemContato mensagemSalva = contatoService.saveMessage(request);

            // Retorna o status 201 Created, que o frontend espera como sucesso
            return new ResponseEntity<>("Mensagem de contato salva. ID: " + mensagemSalva.getId(), HttpStatus.CREATED);

        } catch (Exception e) {
            // Em caso de erro de persistência (ex: banco offline)
            System.err.println("Erro ao salvar mensagem de contato: " + e.getMessage());
            return new ResponseEntity<>("Erro interno ao processar a mensagem.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}
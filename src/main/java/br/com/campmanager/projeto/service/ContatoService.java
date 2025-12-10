package br.com.campmanager.projeto.service;

import org.springframework.stereotype.Service;

import br.com.campmanager.projeto.dto.ContatoRequest;
import br.com.campmanager.projeto.entity.MensagemContato;
import br.com.campmanager.projeto.repositories.MensagemContatoRepository;

import java.time.LocalDateTime;

@Service
public class ContatoService {

    private final MensagemContatoRepository mensagemContatoRepository;

    // Injeção de dependência do Repository
    public ContatoService(MensagemContatoRepository mensagemContatoRepository) {
        this.mensagemContatoRepository = mensagemContatoRepository;
    }

    /**
     * Recebe o DTO (ContatoRequest) do Controller, mapeia para a Entidade
     * e salva a mensagem no banco de dados.
     * 
     * @param request O objeto DTO vindo do frontend.
     * @return A Entidade MensagemContato salva, incluindo o ID e a data de envio.
     */
    public MensagemContato saveMessage(ContatoRequest request) {

        // 1. Mapeamento do DTO para a Entidade JPA
        MensagemContato novaMensagem = new MensagemContato();

        // Copia os dados
        novaMensagem.setNome(request.getNome());
        novaMensagem.setEmail(request.getEmail());
        // Telefone é opcional, então apenas copia o valor (pode ser null/vazio)
        novaMensagem.setTelefone(request.getTelefone());
        novaMensagem.setMensagem(request.getMensagem());

        // Define campos automáticos/defaults se a Entidade não tiver feito (boa
        // prática):
        novaMensagem.setDataEnvio(LocalDateTime.now());
        novaMensagem.setStatus("PENDENTE"); // Define o status inicial

        // 2. Persistência no Banco de Dados
        return mensagemContatoRepository.save(novaMensagem);
    }
}
package br.com.campmanager.projeto.service;

import br.com.campmanager.projeto.dto.contato.ContatoRequest;
import br.com.campmanager.projeto.entity.MensagemContato;
import br.com.campmanager.projeto.repository.MensagemContatoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContatoService {

    private final MensagemContatoRepository mensagemContatoRepository;

    public ContatoService(MensagemContatoRepository mensagemContatoRepository) {
        this.mensagemContatoRepository = mensagemContatoRepository;
    }

    @Transactional
    public void salvarMensagem(ContatoRequest request) {
        MensagemContato mensagem = new MensagemContato();
        mensagem.setNome(request.getNome());
        mensagem.setEmail(request.getEmail());
        // Se o seu DTO não tem getAssunto, remova esta linha:
        // mensagem.setAssunto(request.getAssunto());
        mensagem.setMensagem(request.getMensagem());

        mensagemContatoRepository.save(mensagem);
    }
}
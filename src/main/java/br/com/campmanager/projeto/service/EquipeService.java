package br.com.campmanager.projeto.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.campmanager.projeto.dto.CriarEquipeRequest;
import br.com.campmanager.projeto.entity.Equipe;
import br.com.campmanager.projeto.repositories.EquipeRepository;
import br.com.campmanager.projeto.repositories.UsuarioRepository;

import java.time.LocalDateTime;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência
    public EquipeService(EquipeRepository equipeRepository, UsuarioRepository usuarioRepository) {
        this.equipeRepository = equipeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria uma nova equipe, validando unicidade e definindo o usuário como capitão.
     * 
     * @param request   O DTO com o nome e a tag da equipe.
     * @param capitaoId O ID do usuário autenticado que está criando a equipe.
     * @return A Entidade Equipe salva.
     * @throws Exception se o nome ou a tag já estiverem em uso.
     */
    @Transactional // Garante que as operações de BD sejam atômicas
    public Equipe criarNovaEquipe(CriarEquipeRequest request, Long capitaoId) throws Exception {

        // 1. Validar unicidade do Nome
        if (equipeRepository.findByNome(request.getNomeEquipe()).isPresent()) {
            throw new Exception("Nome de equipe já está em uso.");
        }

        // 2. Validar unicidade da Tag
        if (equipeRepository.findByTag(request.getTagGuilda()).isPresent()) {
            throw new Exception("TAG de guilda já está em uso.");
        }

        // 3. Buscar o Capitão (Usuário)
        Usuario capitao = usuarioRepository.findById(capitaoId)
                .orElseThrow(() -> new Exception("Capitão (Usuário) não encontrado."));

        // 4. Mapear e Criar a Entidade Equipe
        Equipe novaEquipe = new Equipe();
        novaEquipe.setNomeEquipe(request.getNomeEquipe());
        novaEquipe.setTagGuilda(request.getTagGuilda());
        novaEquipe.setCapitao(capitao);
        novaEquipe.setDataCriacao(LocalDateTime.now());
        novaEquipe.setStatus("ATIVA");

        // 5. Salvar a Equipe
        Equipe equipeSalva = equipeRepository.save(novaEquipe);

        // 6. Atualizar o Usuário (Capitão) com a nova Equipe
        capitao.setEquipe(equipeSalva);
        usuarioRepository.save(capitao); // Persiste a mudança no Usuário

        return equipeSalva;
    }

    // Você pode adicionar mais métodos aqui no futuro:
    // - adicionarMembro(Equipe equipe, Usuario membro)
    // - removerMembro(Usuario membro)
    // - buscarEquipePorUsuario(Long usuarioId)
    // - etc.
}
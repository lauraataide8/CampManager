package br.com.campmanager.projeto.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.campmanager.projeto.dto.CriarCampeonatoRequest;
import br.com.campmanager.projeto.entity.Campeonato;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.enums.StatusCampeonato;
import br.com.campmanager.projeto.repositories.CampeonatoRepository;
import br.com.campmanager.projeto.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
    private final UsuarioRepository usuarioRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository, UsuarioRepository usuarioRepository) {
        this.campeonatoRepository = campeonatoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria um novo campeonato no sistema.
     * 
     * @param request   DTO com os detalhes do campeonato.
     * @param criadorId ID do usuário que está criando (obtido do JWT).
     * @return A entidade Campeonato recém-criada.
     * @throws BusinessException se o nome do campeonato já existir ou o criador for
     *                           inválido.
     */
    @Transactional
    public Campeonato criarNovoCampeonato(CriarCampeonatoRequest request, Long criadorId) {

        // 1. Verificar se o nome do campeonato já existe
        Optional<Campeonato> existente = campeonatoRepository.findByNomeIgnoreCase(request.getNome());
        if (existente.isPresent()) {
            throw new BusinessException("Já existe um campeonato com este nome: " + request.getNome());
        }

        // 2. Buscar o usuário criador (garante que o ID é válido)
        Usuario criador = usuarioRepository.findById(criadorId)
                .orElseThrow(() -> new BusinessException("Criador não encontrado. Sessão inválida."));

        // 3. Mapear DTO para Entidade
        Campeonato novoCampeonato = new Campeonato();
        novoCampeonato.setNome(request.getNome());
        novoCampeonato.setFormato(request.getFormato());
        novoCampeonato.setDataInicio(request.getDataInicio());
        novoCampeonato.setDataFim(request.getDataFim());
        novoCampeonato.setMaxEquipes(request.getMaxEquipes());
        novoCampeonato.setCriador(criador);

        // 4. Definir Status inicial (Todos os novos campeonatos começam abertos para
        // inscrição)
        novoCampeonato.setStatus(StatusCampeonato.INSCRICOES_ABERTAS);

        // 5. Salvar e retornar
        return campeonatoRepository.save(novoCampeonato);
    }

    /**
     * Lista todos os campeonatos existentes no sistema.
     * 
     * @return Uma lista de entidades Campeonato.
     */
    @Transactional(readOnly = true)
    public List<Campeonato> listarTodos() {
        return campeonatoRepository.findAll();
    }

    // --- Futuras funcionalidades ---

    // public Campeonato buscarCampeonatoPorId(Long campeonatoId) {...}

    // public Campeonato inscreverEquipe(Long campeonatoId, Long equipeId) {...}

    // public String gerarChaveamento(Long campeonatoId) {...}
}

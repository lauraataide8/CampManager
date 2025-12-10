package br.com.campmanager.projeto.service;

import br.com.campmanager.projeto.dto.ChangePasswordRequest;
import br.com.campmanager.projeto.dto.UsuarioUpdateRequest;
import br.com.campmanager.projeto.entity.Usuario;
import br.com.campmanager.projeto.exception.BusinessException;
import br.com.campmanager.projeto.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Adicionado para criptografia/verificação de senha

    // Injeção de dependências
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método de registro (JÁ EXISTENTE - Mantenha)
    @Transactional
    public Usuario registrarNovoUsuario(Usuario usuario) {
        // ... (Lógica de registro existente) ...
        return usuarioRepository.save(usuario);
    }

    // Método de Login (JÁ EXISTENTE - Mantenha)
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // ====================================================================
    // === NOVOS MÉTODOS PARA A SEÇÃO 'MINHA CONTA' =======================
    // ====================================================================

    /**
     * Busca o perfil do usuário pelo ID. Usado para pré-popular o formulário no
     * frontend.
     * 
     * @param idUsuario ID do usuário (obtido via JWT).
     * @return A entidade Usuario.
     * @throws BusinessException se o usuário não for encontrado.
     */
    public Usuario buscarPerfil(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado. ID inválido ou sessão expirada."));
    }

    /**
     * Atualiza o email, nickname e nome completo do usuário.
     * 
     * @param idUsuario ID do usuário (obtido via JWT).
     * @param request   DTO com os novos dados.
     * @return A entidade Usuario atualizada.
     * @throws BusinessException se o email ou nickname já estiver em uso.
     */
    @Transactional
    public Usuario atualizarPerfil(Long idUsuario, UsuarioUpdateRequest request) {
        Usuario usuario = buscarPerfil(idUsuario); // Já verifica se o usuário existe

        String novoEmail = request.getEmail().toLowerCase();
        String novoNickname = request.getNickname();

        // 1. Verificar se o novo EMAIL já está em uso por outro usuário
        if (!usuario.getEmail().equalsIgnoreCase(novoEmail)) {
            if (usuarioRepository.findByEmail(novoEmail).isPresent()) {
                throw new BusinessException("Este email já está registrado em outra conta.");
            }
        }

        // 2. Verificar se o novo NICKNAME já está em uso por outro usuário
        if (!usuario.getNickname().equalsIgnoreCase(novoNickname)) {
            if (usuarioRepository.findByNickname(novoNickname).isPresent()) {
                throw new BusinessException("Este nickname já está em uso.");
            }
        }

        // 3. Aplicar as alterações
        usuario.setNickname(novoNickname);
        usuario.setEmail(novoEmail);
        usuario.setNomeCompleto(request.getNomeCompleto());

        // 4. Salvar e retornar
        return usuarioRepository.save(usuario);
    }

    /**
     * Permite ao usuário alterar sua senha.
     * 
     * @param idUsuario ID do usuário (obtido via JWT).
     * @param request   DTO com senha atual e nova senha.
     * @throws BusinessException se a senha atual for inválida ou se a nova senha
     *                           for igual à atual.
     */
    @Transactional
    public void alterarSenha(Long idUsuario, ChangePasswordRequest request) {
        Usuario usuario = buscarPerfil(idUsuario); // Já verifica se o usuário existe

        // 1. Verificar a Senha Atual
        if (!passwordEncoder.matches(request.getSenhaAtual(), usuario.getSenhaHash())) {
            // Usa o mesmo status HTTP que um token inválido, impedindo 'força bruta'
            throw new BusinessException("A senha atual fornecida está incorreta.");
        }

        // 2. Verificar se a Nova Senha é diferente da Senha Atual (opcional, mas boa
        // prática)
        if (passwordEncoder.matches(request.getNovaSenha(), usuario.getSenhaHash())) {
            throw new BusinessException("A nova senha deve ser diferente da senha atual.");
        }

        // 3. Criptografar e salvar a Nova Senha
        String novaSenhaCriptografada = passwordEncoder.encode(request.getNovaSenha());
        usuario.setSenhaHash(novaSenhaCriptografada);

        usuarioRepository.save(usuario);

        // Nota: A alteração da senha pode invalidar sessões existentes, o que pode ser
        // tratado
        // pela lógica de segurança do Spring (JWTs expiram, etc.), mas o logout frontal
        // é bom.
    }
}
package br.com.campmanager.projeto.enums;


public enum TipoUsuario {

    ADMIN,      // Acesso total (Gerenciamento de campeonatos globais, usuários)
    ORGANIZER,  // Organizador de campeonatos (pode criar e gerenciar torneios)
    CAPTAIN,    // Membro que é capitão de uma equipe
    MEMBER      // Membro padrão de uma equipe
}

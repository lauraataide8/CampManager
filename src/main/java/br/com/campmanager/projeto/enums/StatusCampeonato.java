package br.com.campmanager.projeto.enums;


public enum StatusCampeonato {

    INSCRICOES_ABERTAS,   // Em fase de recebimento de equipes
    INSCRICOES_FECHADAS,  // As inscrições terminaram, mas o torneio ainda não começou
    AGUARDANDO_INICIO,    // Inscrito, mas a data de início ainda não chegou
    EM_ANDAMENTO,         // Torneio ativo
    FINALIZADO,           // Torneio concluído
    CANCELADO             // Torneio cancelado
}

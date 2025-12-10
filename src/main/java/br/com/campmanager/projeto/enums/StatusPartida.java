package br.com.campmanager.projeto.enums;

public enum StatusPartida {

    AGENDADA,           // A partida foi criada e tem horário/equipes definidas, mas ainda não ocorreu.
    AO_VIVO,            // A partida está sendo jogada ou transmitida no momento (opcional).
    VENCEDOR_DECLARADO, // O resultado foi inserido e o vencedor foi validado.
    EM_DISPUTA,         // Partida sendo contestada ou aguardando verificação.
    ADIADA,             // Partida foi reagendada para uma data posterior.
    WO,                 // Vitória por W.O. (Walkover), quando uma equipe não comparece.
    CANCELADA           // Partida foi permanentemente cancelada.
}


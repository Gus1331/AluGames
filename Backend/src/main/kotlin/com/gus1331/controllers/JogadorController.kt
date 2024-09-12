package com.gus1331.controllers

import com.gus1331.models.Jogador
import com.gus1331.services.JogadorService
import java.time.LocalDate

class JogadorController {
    companion object{
        fun novoJogador(): Jogador {
            return JogadorService.novoJogador()
        }

    }
}
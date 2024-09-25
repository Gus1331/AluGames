package com.gus1331.controllers

import com.gus1331.models.Aluguel
import com.gus1331.models.Jogador
import com.gus1331.models.Jogo
import com.gus1331.services.JogadorService
import java.time.LocalDate

class JogadorController {
    companion object{
        fun novoJogador(): Jogador {
            return JogadorService.novoJogador()
        }

        fun listarJogadores(): List<Jogador>{
            return JogadorService.listarJogadores()
        }

        fun alugarJogo(jogador: Jogador, jogo: Jogo, dataInicio: LocalDate, datafim : LocalDate): Aluguel {
            return JogadorService.alugarJogo(jogador,jogo,dataInicio,datafim)
        }

    }
}
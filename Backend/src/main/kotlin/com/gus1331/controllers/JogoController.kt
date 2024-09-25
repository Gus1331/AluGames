package com.gus1331.controllers

import com.gus1331.models.Jogo
import com.gus1331.services.JogoService
import java.security.Provider.Service

class JogoController {
    companion object{
        fun listarJogos(): List<Jogo>{
            return JogoService.listarJogos()
        }
    }
}
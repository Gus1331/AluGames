package com.gus1331.controllers

import com.gus1331.models.Jogo
import com.gus1331.services.SharkApiService

class SharkApiController {
    companion object{
        fun buscarPorId(id:String): Jogo? {
            return SharkApiService.buscarPorId(id)
        }

        fun buscarPorIdSemDescricao(id:String):Jogo?{
            return SharkApiService.buscarPorIdSemDescricao(id)
        }
    }
}
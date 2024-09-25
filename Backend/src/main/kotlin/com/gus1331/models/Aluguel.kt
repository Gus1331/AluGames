package com.gus1331.models

import java.time.LocalDate
import java.time.Period

class Aluguel(val jogador:Jogador, val jogo:Jogo, val dataInicio:LocalDate = LocalDate.now(), val dataFinal:LocalDate) {
    val valorAluguel:Double = calcularValorAluguel()

    private fun calcularValorAluguel():Double{
        return if(this.jogador.alugueisNoMes(dataInicio.monthValue).size >= jogador.plano.qtdJogosInclusos){
            (jogo.preco!! * Period.between(dataInicio, dataFinal).days)
        } else {
            0.0
        }
    }
    override fun toString(): String {
        return "Aluguel{\n    " +
                "jogador=$jogador,\n    " +
                "jogo=$jogo,\n    " +
                "dataInicio=$dataInicio,\n    " +
                "dataFinal=$dataFinal,\n   " +
                "valorAluguel=$valorAluguel}"
    }
}
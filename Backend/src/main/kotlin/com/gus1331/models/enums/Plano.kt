package com.gus1331.models.enums

enum class Plano(val mensalidade:Double, val qtdJogosInclusos: Int, val descontoReputacao: Double) {
    BRONZE(0.0, 0, 0.1),
    PRATA(9.9,3, 0.15),
    OURO(19.9, 5, 0.2),
    DIAMANTE(29.9, 10, 0.3),
    PLATINA(39.9, 20, 0.5);


}
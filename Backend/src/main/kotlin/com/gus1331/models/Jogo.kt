package com.gus1331.models

class Jogo (
    val titulo:String,
    val capa:String){

    var descricao:String? = "$titulo, O jogo."
    override fun toString(): String {
        return "Jogo{\n    titulo='$titulo',\n    capa='$capa',\n    descricao=$descricao\n}"
    }
}

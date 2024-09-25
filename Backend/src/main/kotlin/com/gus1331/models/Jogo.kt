package com.gus1331.models

class Jogo (
    val titulo:String,
    val capa:String){

    var descricao:String? = "$titulo, O jogo."
    var preco:Double? = null

    constructor(titulo: String, capa:String, descricicao:String, preco:Double):this(titulo, capa){
        this.descricao = descricao
        this.preco = preco
    }
    override fun toString(): String {
        return "Jogo{\n    titulo='$titulo',\n    capa='$capa',\n   preco: $preco,\n    descricao=$descricao}"
    }
}

package com.gus1331.models

class Jogo (
    val titulo:String,
    val capa:String){


    var id: Int? = null
    var descricao:String? = "$titulo, O jogo."
    var preco:Double? = null

    constructor(id: Int, titulo: String, capa:String, descricao:String, preco:Double):this(titulo, capa){
        this.id = id
        this.descricao = descricao
        this.preco = preco
    }
    override fun toString(): String {
        return "Jogo{\n   id='$id',\n    titulo='$titulo',\n    capa='$capa',\n   preco: $preco,\n    descricao=$descricao}"
    }
}

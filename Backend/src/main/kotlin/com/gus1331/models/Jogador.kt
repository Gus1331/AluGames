package com.gus1331.models

import java.time.LocalDate

data class Jogador (var nome:String, var email:String){
    var dataNascimento:LocalDate? = null
    var usuario:String? = null
    val idJogador:Int? = null


    constructor(nome: String, email: String, dataNascimento:LocalDate, usuario:String):
            this(nome, email){
                this.dataNascimento = dataNascimento
                this.usuario = usuario
            }

    override fun toString(): String {
        return "Jogador(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idJogador=$idJogador)"
    }
}
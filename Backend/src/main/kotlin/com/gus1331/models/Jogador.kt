package com.gus1331.models

import com.gus1331.controllers.SharkApiController
import com.gus1331.models.enums.Plano
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

data class Jogador(var nome: String, var email: String) {

    var dataNascimento: LocalDate? = null

    var usuario: String? = null
        set(value) {
            field = value
            if (this.idJogador.isNullOrBlank()) gerarIdInterno()
        }

    var idJogador: String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo>()

    val jogosAlugados = mutableListOf<Aluguel>()
        get() {
            return field
        }

    var plano:Plano = Plano.BRONZE
        set(value){
            field = value
        }

    constructor(nome: String, email: String, dataNascimento: LocalDate, usuario: String) :
            this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        gerarIdInterno()
    }

    init {
        val sc = Scanner(System.`in`)
        while (nome.trim().isBlank()) {
            println("Nome inválido\n")

            println("Insira o nome novamente: ")
            nome = sc.nextLine()
        }
        while (!validarEmail(email)) {
            println("Email inválido\n")

            println("Insira o email novamente: ")
            email = sc.nextLine()
        }
    }


    fun pesquisarJogo() {
        if (usuario == null) {
            println("O jogador $nome precisa terminar o seu cadastro antes de pesquisar")
            return
        }
        val sc = Scanner(System.`in`)
        println("Pesquise por um jogo: \n")

        val resultadoPesquisa = SharkApiController.pesquisarPorTitulo(sc.nextLine())

        println("\nJogos encontrados:\n")
        var i = 1
        resultadoPesquisa.forEach {
            println("$i - ${it.titulo}")
            jogosBuscados.add(it)
            i++
        }
    }

    private fun validarEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
        return emailRegex.matches(email)
    }

    fun alugarJogo(aluguel: Aluguel) {
        if(aluguel.jogador.idJogador != this.idJogador){
            throw Exception("Este alguem não foi atrelado a este jogador, Jogador:$idJogador, Aluguel:${aluguel.jogador.idJogador} ")
        }
        jogosAlugados.add(aluguel)
    }

    fun alugueisNoMes(mes: Int):List<Aluguel>{
        return jogosAlugados.filter { aluguel -> aluguel.dataInicio.monthValue == mes }
    }

    private fun gerarIdInterno() {
        val numero = Random.nextInt(1000)
        val codigo = String.format("%04d", numero)
        val prefixo = "" + usuario?.get(0) + usuario?.get(1) + usuario?.get(2)
        idJogador = prefixo + codigo
    }

    override fun toString(): String {
        return "Jogador{\n    nome='$nome',\n    email='$email',\n    dataNascimento=$dataNascimento,\n    usuario=$usuario,\n    idJogador=$idJogador}"
    }
}
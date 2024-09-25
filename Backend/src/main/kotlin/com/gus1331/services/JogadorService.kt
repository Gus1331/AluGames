package com.gus1331.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gus1331.models.Aluguel
import com.gus1331.models.Jogador
import com.gus1331.models.Jogo
import com.gus1331.models.jsoninfo.JogadorInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Scanner

class JogadorService {
    companion object{
        fun listarJogadores():List<Jogador>{
            var listaJogadorInfo = mutableListOf<JogadorInfo>()
            val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"

            val client: HttpClient = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build()
            val response = client
                .send(request, HttpResponse.BodyHandlers.ofString())

            val json = response.body()

            val gson = Gson()

            val jogadorTipo = object : TypeToken<List<JogadorInfo>>() {}.type
            listaJogadorInfo = gson.fromJson(json, jogadorTipo)

            var res = mutableListOf<Jogador>()
            listaJogadorInfo.forEach {
                val dataNascimento = LocalDate.parse(it.dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                var jogador= Jogador(it.nome, it.email, dataNascimento, it.usuario)
                res.add(jogador)
            }
            return res
        }

        fun alugarJogo(jogador: Jogador, jogo: Jogo, dataInicio: LocalDate, dataFinal: LocalDate): Aluguel{
            val aluguel = Aluguel(jogador, jogo, dataInicio, dataFinal)
            jogador.alugarJogo(aluguel)
            return aluguel
        }

        fun novoJogador(): Jogador {
            val sc = Scanner(System.`in`)

            println("Cadastre um novo jogador...")

            println("\nDigite seu nome:")
            val nome = sc.nextLine()

            println("\nDigite seu email:")
            val email = sc.nextLine()

            var jogador:Jogador = Jogador(nome, email)

            println("Deseja realizar o cadastro completo? s/n")

            if(sc.nextLine().equals("s", true)){
                println("\nDigite a sua data de nascimento: (dd-mm-yyyy)")
                var dataNascimentoString = sc.nextLine()
                var dataNascimento:LocalDate? = null

                // apartir daqui, o objetivo era treinar runCatching
                val conversaoData = runCatching {
                    dataNascimento = LocalDate.parse(dataNascimentoString, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }

                var novaTentativaIsSuccess = false

                conversaoData.onFailure {
                    println("Formato de data inválido, deseja tentar novamente? s/n")
                    if(sc.nextLine().equals("s", true)){

                        println("Digite novamente a sua data de nascimento: (dd-mm-yyy, Exemplo: 01/01/2000)")
                        dataNascimentoString = sc.nextLine()


                        val novaTentativa = runCatching {
                            dataNascimento = LocalDate.parse(dataNascimentoString, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        }

                        novaTentativa.onFailure {
                            println("Formato de data inválido")
                            println("Interrompendo cadastro por excesso de tentativas")
                            return jogador
                        }

                        novaTentativa.onSuccess {
                            novaTentativaIsSuccess = true
                        }
                    }
                }

                var usuario:String? = null

                if(conversaoData.isSuccess || novaTentativaIsSuccess) {
                    if(dataNascimento != null){
                        println("\nDigite seu nome de usuário: ")

                        jogador.let{
                            it.dataNascimento = dataNascimento
                            it.usuario = sc.nextLine()
                        }
                    }
                }
            }

            return jogador
        }
    }
}


/*
    MÉTODO PARA CONFIRMAR UM DADO
    recebe um Object? e retorna Object!
    caso nulo, devolve um erro
            fun confirmarData(data:LocalDate?):LocalDate{
                return data?: throw IllegalArgumentException("A data não pode ser nula")
            }
 */
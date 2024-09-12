package com.gus1331.services

import com.gus1331.models.Jogador
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Scanner

class JogadorService {
    companion object{
        fun novoJogador(): Jogador {
            val sc = Scanner(System.`in`)

            println("Cadastre um novo jogador...")

            println("\nDigite seu nome:")
            val nome = sc.nextLine()

            println("\nDigite seu email:")
            val email = sc.nextLine()

            var jogador:Jogador = Jogador(nome, email)

            println("\nDigite a sua data de nascimento: (dd-mm-yyyy)")
            var dataNascimentoString = sc.nextLine()
            var dataNascimento:LocalDate? = null

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
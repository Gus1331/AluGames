package com.gus1331.services

import com.google.gson.Gson
import com.gus1331.models.Jogo
import com.gus1331.models.cheapsharkapi.ApiData
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

class SharkApiService {
    companion object{
        fun buscarPorId(id:String): Jogo?{
            val sc = Scanner(System.`in`)

            val client: HttpClient = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=$id"))
                .build()
            val response = client
                .send(request, HttpResponse.BodyHandlers.ofString())
            val json = response.body()
            val gson = Gson()

            var jogo:Jogo? = null

            val requisition = runCatching {
                val responseData = gson.fromJson(json, ApiData::class.java);
                jogo = Jogo(responseData.info.title, responseData.info.thumb)
            }

            requisition.onFailure{
                 println("Requisição a api falhou, o id é inválido;")
            }

            requisition.onSuccess {
                println("O jogo encontrado foi: ${jogo?.titulo}\n")
                println("Deseja adicionar uma descrição personalizada ao jogo? s/n")
                if(sc.nextLine().equals("s", true)){
                    println("\nEscreva a descrição do jogo:")
                    jogo?.descricao = sc.nextLine()
                } else {
                    jogo?.descricao = "\nJogo ${jogo?.titulo}"
                }
            }
            return jogo
        }

        fun buscarPorIdSemDescricao(id:String):Jogo?{
            val client: HttpClient = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=$id"))
                .build()
            val response = client
                .send(request, HttpResponse.BodyHandlers.ofString())
            val json = response.body()
            val gson = Gson()

            var jogo:Jogo? = null

            val requisition = runCatching {
                val responseData = gson.fromJson(json, ApiData::class.java)
                jogo = Jogo(responseData.info.title, responseData.info.thumb)
            }

            requisition.onFailure{
                println("Requisição a api falhou, meu jogo favorito sumiu :(;")
            }

            requisition.onSuccess {
                jogo?.descricao = "Jogo ${jogo?.titulo}"
            }
            return jogo
        }
    }
}
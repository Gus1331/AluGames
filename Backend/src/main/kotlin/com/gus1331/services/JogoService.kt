package com.gus1331.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gus1331.models.Jogo
import com.gus1331.models.jsoninfo.JogadorInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class JogoService {
    companion object{
        fun listarJogos(): List<Jogo>{

            var res = mutableListOf<Jogo>()
            val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/jogos.json"

            val client: HttpClient = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build()
            val response = client
                .send(request, HttpResponse.BodyHandlers.ofString())

            val json = response.body()

            val gson = Gson()

            val jogoTipo = object : TypeToken<List<Jogo>>() {}.type
            res = gson.fromJson(json, jogoTipo)

            return res
        }
    }
}
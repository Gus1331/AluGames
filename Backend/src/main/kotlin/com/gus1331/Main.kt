package com.gus1331

import com.gus1331.controllers.SharkApiController
import com.gus1331.controllers.JogadorController
import com.gus1331.controllers.JogoController
import com.gus1331.database.dao.JogoDAO
import com.gus1331.database.Conexao
import com.gus1331.models.Jogador
import com.gus1331.models.enums.Plano
import java.time.LocalDate
import java.util.*

fun main() {
    val conexao = Conexao(Conexao.iniciarConexao()).conn
    val jogoDAO = JogoDAO(conexao)
    val sc = Scanner(System.`in`)
    // pesquisar(sc)

    val jogadores = JogadorController.listarJogadores()
    val jogos = jogoDAO.getJogos()

}

fun pesquisar(sc: Scanner){
    println("Hello World!!!\n\n")

    val jogador = JogadorController.novoJogador()

    do{
        jogador.pesquisarJogo()

        println("\nDeseja continuar pesquisando? s/n")
    } while (sc.next().equals("s", true))


    println(jogador.jogosBuscados)
}






























/*
    ARQUIVO ANTES DA REFATORAÇÃO (Para minha consulta pessoal de estudo)

    fun main(args: Array<String>) {

    // 1o modulo
    println("Hello World!!!")

    val client: HttpClient = HttpClient.newHttpClient()
    val requestExample = HttpRequest.newBuilder()
        .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=230937"))
        .build()

    val responseExample = client
        .send(requestExample, HttpResponse.BodyHandlers.ofString())

    val jsonExample = responseExample.body()

    /*
    val jogoFavorito = com.Gus1331.Jogo()
    jogoFavorito.titulo = ""
    jogoFavorito.capa = ""

    val jogoFavorito = com.Gus1331.Jogo("","")
    println(jogoFavorito)
     */

    // 2o modulo

    val gson = Gson()
    val infoJogoExample = gson.fromJson(jsonExample, InfoJogo::class.java)
    // com.Gus1331.Jogo::class.java -> aponta a classse InfoJogo

    val meuJogo = com.Gus1331.Jogo(titulo = infoJogoExample.info.title, capa = infoJogoExample.info.thumb)
    println("Este é o meu jogo favorito -> $meuJogo \n")

    // 3o modulo
    val sc = Scanner(System.`in`)

    val buscarJogoPorIdEndpoint = "https://www.cheapshark.com/api/1.0/games?id="

    println("Digite um código de jogo para buscar")

    val buscaDoUsuario = sc.nextLine()
    /*
    do{
        println("Id inválido")
        val buscaDoUsuario = sc.nextLine()
    }while (buscaDoUsuario.toInt() < 1 || buscaDoUsuario.toInt() > 237998)
    // 237998 maior numero atual de id de jogo
     */


    // REQUISIÇÃO
    val request = HttpRequest.newBuilder()
        .uri(URI.create(buscarJogoPorIdEndpoint + buscaDoUsuario))
        .build()
    val response = client
        .send(request, HttpResponse.BodyHandlers.ofString())
    val json = response.body()

    var jogoUsuario:com.Gus1331.Jogo? = null

    // RUNCATCHING
    val buscarJogo = runCatching {
        val infoJogo = gson.fromJson(json, InfoJogo::class.java)
        jogoUsuario = com.Gus1331.Jogo(titulo = infoJogo.info.title, capa = infoJogo.info.thumb)
    }

    buscarJogo.onFailure {
        println("Id inválido")
    }

    buscarJogo.onSuccess {
        println("O jogo com ID $buscaDoUsuario = $jogoUsuario\n")

        println("Deseja adicionar uma descrição a este jogo? S/N")
        if (sc.nextLine().equals("S", true)){
            println("\nPor favor, insira a descrição personalizada: ")
            val descricao = sc.nextLine();
            jogoUsuario?.descricao = descricao
        } else {
            jogoUsuario?.descricao = jogoUsuario?.titulo
        }

        println(jogoUsuario);
    }
}




        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <!-- depedencia para desserialização de JSON -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.11.0</version>
        </dependency>

*/

/*
val meuJogador = Jogador("Gus", "gus@email.com")
val meuJogoFavorito = SharkApiController.buscarPorIdSemDescricao("230937")

println("Olá, me chamo Gus\nMeu jogo favorito é \n$meuJogoFavorito\n")

println("Busque o seu jogo favorito pelo id:")

println(SharkApiController.buscarPorId(sc.nextLine()))


val jogador = JogadorController.novoJogador()


println(jogador)

 */
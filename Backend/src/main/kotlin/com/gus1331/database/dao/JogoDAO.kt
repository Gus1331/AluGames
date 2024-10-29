package com.gus1331.database.dao

import com.gus1331.models.Jogo
import java.sql.Connection
import java.sql.ResultSet

class JogoDAO(val connection: Connection) {

    private fun resultToList(result: ResultSet): MutableList<Jogo> {
        val res = mutableListOf<Jogo>()

        while(result.next()){
            val jogo = Jogo(
                result.getInt("id"),
                result.getString("titulo"),
                result.getString("capa"),
                result.getString("descricao"),
                result.getDouble("preco")
            )
            res.add(jogo)
        }
        return res;
    }
    fun getJogos():List<Jogo>{
        var res = mutableListOf<Jogo>()

        try{
            val statement = connection.createStatement()
            val result = statement.executeQuery("SELECT * FROM Jogos")
            res = resultToList(result);
            statement.close()
        }catch (e: Exception){
            println("Erro de consulta: $e")
        }

        return res
    }

}
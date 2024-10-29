package com.gus1331.database
import com.gus1331.models.Jogo
import java.sql.Connection
import java.sql.DriverManager
import java.time.LocalDate

class Conexao(val connection: Connection) {
    val conn: Connection
        get() {
            println("CONN")
            return connection
        }
    companion object{
        fun iniciarConexao():Connection {

            var connection:Connection? = null

            val conectando = runCatching {
            val url = "jdbc:sqlserver://gus1331.database.windows.net:1433;database=AluGames;XXXXXXXXX;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                connection = DriverManager.getConnection(url);
            }

            conectando.onSuccess {
                println("Conexão estabelecida com sucesso!")
            }

            conectando.onFailure {
                throw Exception("Banco de dados não conectado!\n Erro: ${it.message}")
            }
            return connection!!
        }
    }
}
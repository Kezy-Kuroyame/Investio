package for_sql
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionDB {
    private val url = "jdbc:postgresql://localhost:5432/postgres/investment"
    private val username = "postgres"
    private val password = "200319792003saa2003"
    private var connection: Connection? = null

    init {
        try {
            connection = DriverManager.getConnection(url, username, password)
            if (connection != null) {
                println("Соединение с базой данных установлено")
            }
        } catch (e: SQLException) {
            println("Ошибка при установлении соединения с базой данных: ${e.message}")
        }
    }

    fun closeConnection() {
        try {
            connection?.close()
            println("Соединение с базой данных закрыто")
        } catch (e: SQLException) {
            println("Ошибка при закрытии соединения: ${e.message}")
        }
    }

    fun isDatabaseConnected(): Boolean {
        try {
            connection = DriverManager.getConnection(url, username, password)
            return true
        } catch (e: SQLException) {
            return false
        }
    }
}
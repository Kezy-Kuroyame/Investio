package for_sql

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.DriverManager


//    private const val URL = "192.168.56.1"
//    private var retrofit: Retrofit? = null
//    fun getConnection(): Retrofit? {
//        if (retrofit == null) {
//            retrofit =
//                Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
//                    .build()
//        }
//        return retrofit
//    }

public class ConnectionDB{
//      val url = "jdbc:postgresql://localhost/investment"
//      val connection = DriverManager.getConnection(url, "postgres", "12345")
//    val statement = connection.createStatement()
//    val resultSet = statement.executeQuery("""SELECT id, name, email, id_password, date
//FROM public."ref_User";""")
    fun prrr(): String{
        val url = "jdbc:postgresql://localhost/investment"
        val connection = DriverManager.getConnection(url, "postgres", "200319792003saa2003")
    //    val statement = connection.createStatement()
    //    val resultSet = statement.executeQuery("""SELECT id, name, email, id_password, date
    //FROM public."ref_User";""")
        return "aaaa"
    }
}
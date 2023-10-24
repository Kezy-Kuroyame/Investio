package ktor.ktoring.models

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class User(val id: String, val name: String, val email: String, val password: Int,
                val date: Date)

val users = mutableListOf<User>()



// deleted password id



/*@Serializable
data class Password(val id: Int, val password: String)

val passwords = mutableListOf<Password>()
*/
package ktor.ktoring.routes


import ktor.ktoring.models.users
import ktor.ktoring.models.User


import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText

import io.ktor.server.routing.Route

import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.delete
import io.ktor.server.routing.route


fun Route.userRouting() {

    route("/user") {

        /**
         * GET делает запрос на http сайтик и получает для нас какой-то ответ
         *
         * Пример работы GET:
         * GET http://www.example.com/users  -  получаем всех юзеров
         * GET http://www.example.com/users/12345  -  получаем юзера 12345
         * ИЛИ
         * GET http://www.example.com/customers?name=лера  -  то же самое что до этого
         *
         * Выше
         * Первый GET получает список всех пользователей
         * Второй GET получает конкретного пользователя
         *
         * Пример работы GET:
         * curl -X GET --location "http://127.0.0.1:8080/user" -H "Accept: application/json"
         */


        get {
            if (users.isNotEmpty()) {
                call.respond(users)
            } else {
                call.respondText("No users found", status = HttpStatusCode.OK)
            }
        }


        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val customer =
                users.find { it.id == id } ?: return@get call.respondText(
                    "No user with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(customer)
        }


        /**
         * POST добавляет на сайт что-то новое.
         * Этот POST добавляет одного нового пользователя
         *
         * Пример работы POST:
         * curl -X POST --location "http://127.0.0.1:8080/user"  \
         *     -H "Content-Type: application/json"  \
         *     -d "{
         *           \"id\": \"338828\",
         *           \"firstName\": \"Лера\",
         *           \"lastName\": \"Серебренникова\",
         *           \"email\": \"лерин_имейл@майл.ру\"
         *         }"
         */


        post {
            val customer = call.receive<User>()
            users.add(customer)
            call.respondText("User added correctly", status = HttpStatusCode.Created)
        }


        /**
         * DELETE удаляет что-то с сайта.
         * Этот DELETE удаляет пользователя из списка "пользователи"
         *
         * Пример работы DELETE:
         * curl -X DELETE --location "http://127.0.0.1:8080/customer/11111"
         */


        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (users.removeIf { it.id == id }) {
                call.respondText("User removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}



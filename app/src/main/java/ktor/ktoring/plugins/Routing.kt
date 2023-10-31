package ktor.ktoring.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import ktor.ktoring.routes.userRouting

fun Application.configureRouting() {
    routing {
        userRouting()
    }
}
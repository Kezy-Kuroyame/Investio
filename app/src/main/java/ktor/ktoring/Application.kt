package ktor.ktoring

import io.ktor.server.application.Application
import ktor.ktoring.plugins.configureRouting
import ktor.ktoring.plugins.configureSerialization

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}
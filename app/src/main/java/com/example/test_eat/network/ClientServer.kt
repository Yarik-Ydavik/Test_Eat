package com.example.test_eat.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ClientServer {
    val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                coerceInputValues = true
            })
        }
    }
}
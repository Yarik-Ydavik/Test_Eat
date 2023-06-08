package com.example.test_eat.network.request

import com.example.test_eat.data.dishes.Dishes
import com.example.test_eat.network.ClientServer
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DishesRequest {
    suspend fun getListDishes(): Dishes{
        val response: HttpResponse = ClientServer.client.get("https://run.mocky.io/v3/c7a508f2-a904-498a-8539-09d96785446e")
        return Json.decodeFromString(response.bodyAsText())
    }
}
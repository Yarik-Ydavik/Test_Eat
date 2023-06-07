package com.example.test_eat.network.request

import com.example.test_eat.data.Kitchens_data.Kitchens
import com.example.test_eat.network.ClientServer
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class HomeRequest {
    suspend fun getListKitchen () : Kitchens{
        val response: HttpResponse = ClientServer.client.request("https://run.mocky.io/v3/058729bd-1402-4578-88de-265481fd7d54")
        return  Json.decodeFromString(response.bodyAsText())
    }
}
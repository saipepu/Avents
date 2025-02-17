package com.example.avents.network

import com.example.avents.model.GetUserByIdResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ApiService {
    private val baseUrl = "https://events-au-v2.vercel.app"

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }) // Use Kotlinx Serialization with JSON
        }
    }

    suspend fun getUserById(id: String): GetUserByIdResponse {
        return client.get("$baseUrl/user/$id").body()
    }

    fun closeClient() {
        client.close()
    }
}
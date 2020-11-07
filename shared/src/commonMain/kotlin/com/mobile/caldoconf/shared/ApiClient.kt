package com.mobile.caldoconf.shared

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class ApiClient {
    // No engine is passed into `HttpClient()` since it will be decided
    // at compile time if it is used the Android or the iOS client
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    companion object {
        private const val CALDOCONF_ENDPOINT = "http://127.0.0.1:8080/"
    }

    suspend fun getEvents(): List<Event> {
        return httpClient.get(CALDOCONF_ENDPOINT)
    }
}
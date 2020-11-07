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
        // This is temporal until we have a real service
        // To address the local service running in the computer from the
        // Android emulator, the Api Client must point to `10.0.2.2` IP
        // More info: https://developer.android.com/studio/run/emulator-networking.html
        private const val CALDOCONF_ENDPOINT = "http://10.0.2.2:8080/conferences"
    }

    suspend fun getEvents(): List<Event> = httpClient.get(CALDOCONF_ENDPOINT)
}
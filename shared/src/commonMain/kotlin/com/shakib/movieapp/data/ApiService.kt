package com.shakib.movieapp.data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ApiService(private val httpClient: HttpClient) {
    suspend fun getPopularMovies(page: Int): HttpResponse = httpClient.get {
        url {
            appendPathSegments("popular")
            parameters.append(name = "page", value = page.toString())
        }
    }

    suspend fun getRecommendations(id: String): HttpResponse = httpClient.get {
        url { appendPathSegments("$id/recommendations") }
    }
}

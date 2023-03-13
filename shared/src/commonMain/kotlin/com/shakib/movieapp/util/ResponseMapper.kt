package com.shakib.movieapp.util

import com.shakib.movieapp.domain.model.DomainModel
import com.shakib.movieapp.platform.MPLogger
import io.ktor.client.statement.*

suspend inline fun <reified T> mapResponse(
    response: DomainModel<T>,
    block: () -> HttpResponse
): DomainModel<T> {
    try {
        val result = block.invoke()
        MPLogger.d("mapResponse", "Result - $result")
        response.payload = result.parse()
        response.isSuccess = true
    } catch (e: Exception) {
        MPLogger.e("mapResponse", "Caught - ${e.message}")
        response.error = e.parse()
    }
    return response
}

package com.shakib.movieapp.util

import com.shakib.movieapp.data.dto.ErrorDTO
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*


fun Exception.parse(): Exception {
    return if (this is NetworkException)
        this
    else
        UnknownException("An unknown exception occurred \n Check your network connectivity or contact with responsible person.")
}

suspend inline fun <reified T : Any> HttpResponse.parse(): T {
    if (status.isSuccess())
        return body()
    else if (status == HttpStatusCode.BadRequest || status == HttpStatusCode.Unauthorized || status == HttpStatusCode.Forbidden || status == HttpStatusCode.NotFound || status == HttpStatusCode.ServiceUnavailable)
        throw NetworkException(body<ErrorDTO>().status_message)
    else
        throw UnknownException(body<ErrorDTO>().status_message)
}

// 400, 401, 403, 404, 500
data class NetworkException(override val message: String?) : Exception()

// Unknown
data class UnknownException(override val message: String?) : Exception()

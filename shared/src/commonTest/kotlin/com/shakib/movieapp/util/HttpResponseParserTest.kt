package com.shakib.movieapp.util

import com.shakib.movieapp.BaseMockKUnitTest
import com.shakib.movieapp.data.dto.ErrorDTO
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class HttpResponseParserTest : BaseMockKUnitTest() {
    @Test
    fun `should parse HTTP response with success status`() = runTest {
        // Arrange
        val httpResponse = mockk<HttpResponse>()
        val body = Any()
        coEvery { httpResponse.status } returns HttpStatusCode.OK
        coEvery { httpResponse.body<Any>() } returns body

        // Act
        val result: Any = httpResponse.parse()

        // Assert
        assertEquals(body, result)
    }

    @Test
    fun `should throw NetworkException for bad request`() = runTest {
        // Arrange
        val httpResponse = mockk<HttpResponse>()
        val error = HttpStatusCode.BadRequest.toError()
        coEvery { httpResponse.status } returns HttpStatusCode.BadRequest
        coEvery { httpResponse.body<ErrorDTO>() } returns error

        // Act & Assert
        assertFailsWith(NetworkException::class) {
            httpResponse.parse()
        }.also {
            assertEquals(error.status_message, it.message)
        }
    }

    @Test
    fun `should throw NetworkException for unauthorized`() = runTest {
        // Arrange
        val httpResponse = mockk<HttpResponse>()
        val error = HttpStatusCode.Unauthorized.toError()
        coEvery { httpResponse.status } returns HttpStatusCode.Unauthorized
        coEvery { httpResponse.body<ErrorDTO>() } returns error

        // Act & Assert
        assertFailsWith(NetworkException::class) {
            httpResponse.parse()
        }.also {
            assertEquals(error.status_message, it.message)
        }
    }

    @Test
    fun `should throw NetworkException for forbidden`() = runTest {
        // Arrange
        val httpResponse = mockk<HttpResponse>()
        val error = HttpStatusCode.Forbidden.toError()
        coEvery { httpResponse.status } returns HttpStatusCode.Forbidden
        coEvery { httpResponse.body<ErrorDTO>() } returns error

        // Act & Assert
        assertFailsWith(NetworkException::class) {
            httpResponse.parse()
        }.also {
            assertEquals(error.status_message, it.message)
        }
    }

    @Test
    fun `should throw NetworkException for not found`() = runTest {
        // Arrange
        val httpResponse = mockk<HttpResponse>()
        val error = HttpStatusCode.NotFound.toError()
        coEvery { httpResponse.status } returns HttpStatusCode.NotFound
        coEvery { httpResponse.body<ErrorDTO>() } returns error

        // Act & Assert
        assertFailsWith(NetworkException::class) {
            httpResponse.parse()
        }.also {
            assertEquals(error.status_message, it.message)
        }
    }

    @Test
    fun `should throw NetworkException for service unavailable`() = runTest {
        // Arrange
        val httpResponse = mockk<HttpResponse>()
        val error = HttpStatusCode.ServiceUnavailable.toError()
        coEvery { httpResponse.status } returns HttpStatusCode.ServiceUnavailable
        coEvery { httpResponse.body<ErrorDTO>() } returns error

        // Act & Assert
        assertFailsWith(NetworkException::class) {
            httpResponse.parse()
        }.also {
            assertEquals(error.status_message, it.message)
        }
    }

    @Test
    fun `should throw UnknownException for unknown status`() = runTest {
        // Arrange
        val httpResponse = mockk<HttpResponse>()
        val error = HttpStatusCode.InternalServerError.toError()
        coEvery { httpResponse.status } returns HttpStatusCode.InternalServerError
        coEvery { httpResponse.body<ErrorDTO>() } returns error

        // Act & Assert
        assertFailsWith(UnknownException::class) {
            httpResponse.parse()
        }.also {
            assertEquals(error.status_message, it.message)
        }
    }


    // Helper function
    private fun HttpStatusCode.toError() = ErrorDTO(value, description, false)
}

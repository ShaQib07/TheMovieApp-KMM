package com.shakib.movieapp.util

import com.shakib.movieapp.BaseMockKUnitTest
import com.shakib.movieapp.domain.model.DomainModel
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class ResponseMapperTestK : BaseMockKUnitTest() {
    @Test
    fun `should set payload and isSuccess true for successful response`() = runTest {
        // Arrange
        val block = mockk<() -> HttpResponse>()
        val response = DomainModel<String>()
        val httpStatus = HttpStatusCode.OK
        val responseBody = "Response Body"
        val httpResponse = mockk<HttpResponse> {
            every { status } returns httpStatus
            coEvery { body<String>() } returns responseBody
        }

        coEvery { block.invoke() } returns httpResponse

        // Act
        val result = mapResponse(response, block)

        // Assert
        coVerify { block.invoke() }
        assertTrue(result.isSuccess)
        assertNull(result.error)
        assertEquals(responseBody, result.payload)
    }

    @Test
    fun `should set error for NetworkException`() = runTest {
        // Arrange
        val block = mockk<() -> HttpResponse>()
        val response = DomainModel<String>()
        val message = "Network Exception Occurred"
        val exception = NetworkException(message)

        coEvery { block.invoke() } throws exception

        // Act
        val result = mapResponse(response, block)

        // Assert
        coVerify { block.invoke() }
        assertFalse(result.isSuccess)
        assertNotNull(result.error)
        assertTrue(result.error is NetworkException)
        assertEquals(message, result.error?.message)
    }

    @Test
    fun `should set error for UnknownException`() = runTest {
        // Arrange
        val block = mockk<() -> HttpResponse>()
        val response = DomainModel<String>()
        val message = "An unknown exception occurred \n" +
                "Check your network connectivity or contact with responsible person."
        val exception = Exception(message)

        coEvery { block.invoke() } throws exception

        // Act
        val result = mapResponse(response, block)

        // Assert
        coVerify { block.invoke() }
        assertFalse(result.isSuccess)
        assertNotNull(result.error)
        assertTrue(result.error is UnknownException)
        assertEquals(message, result.error?.message)
    }
}

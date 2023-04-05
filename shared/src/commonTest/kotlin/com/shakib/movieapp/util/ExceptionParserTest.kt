package com.shakib.movieapp.util

import com.shakib.movieapp.BaseMockKUnitTest
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ExceptionParserTest : BaseMockKUnitTest() {
    @Test
    fun `should return NetworkException`() {
        // Arrange
        val networkException = mockk<NetworkException>(relaxed = true)
        val exception: Exception = networkException

        // Act
        val result = exception.parse()

        // Assert
        assertEquals(networkException, result)
    }

    @Test
    fun `should return UnknownException`() {
        // Arrange
        val message = "Unknown error"
        val exception = Exception(message)

        // Act
        val result = exception.parse()

        // Assert
        assertTrue(result is UnknownException)
        assertEquals(
            "An unknown exception occurred \nCheck your network connectivity or contact with responsible person.",
            result.message
        )
    }
}

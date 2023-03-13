package com.shakib.movieapp.platform

import io.ktor.client.plugins.logging.*

internal expect class PlatformLogger() {

    fun debug(tag: String, message: String)

    fun error(tag: String, message: String)
}

object MPLogger {

    private val logger = PlatformLogger()

    fun d(tag: String, message: String) {
        logger.debug(tag, message)
    }

    fun e(tag: String, message: String) {
        logger.error(tag, message)
    }
}

object HttpClientLogger : Logger {

    private const val TAG = "HttpClientLogger"
    override fun log(message: String) {
        MPLogger.d(TAG, message)
    }
}

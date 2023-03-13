package com.shakib.movieapp.data.dto

@kotlinx.serialization.Serializable
data class ErrorDTO(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)

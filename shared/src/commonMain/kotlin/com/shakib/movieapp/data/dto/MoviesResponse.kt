package com.shakib.movieapp.data.dto

@kotlinx.serialization.Serializable
data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

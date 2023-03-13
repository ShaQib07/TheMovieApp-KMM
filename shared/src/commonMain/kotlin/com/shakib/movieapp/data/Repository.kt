package com.shakib.movieapp.data

import com.shakib.movieapp.util.mapResponse
import com.shakib.movieapp.data.dto.MoviesResponse
import com.shakib.movieapp.domain.model.DomainModel

class Repository(private val apiService: ApiService) {
    suspend fun getPopularMovies(page: Int): DomainModel<MoviesResponse> =
        mapResponse(DomainModel()) { apiService.getPopularMovies(page) }
}

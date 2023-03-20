package com.shakib.movieapp.data

import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.data.dto.MoviesResponse
import com.shakib.movieapp.domain.model.DomainModel
import com.shakib.movieapp.util.mapResponse

class Repository(private val apiService: ApiService, private val dbHelper: DBHelper) {
    suspend fun getPopularMovies(page: Int): DomainModel<MoviesResponse> =
        mapResponse(DomainModel()) { apiService.getPopularMovies(page) }

    suspend fun getRecommendations(id: String): DomainModel<MoviesResponse> =
        mapResponse(DomainModel()) { apiService.getRecommendations(id) }

    fun getFavoriteMovies() = dbHelper.fetchAllMovies()

    fun addToFavorites(movie: Movie) = dbHelper.insertMovie(movie)

    fun removeFromFavorites(movie: Movie) = dbHelper.deleteMovie(movie)
}

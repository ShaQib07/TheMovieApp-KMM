package com.shakib.movieapp.domain

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.shakib.movieapp.data.Repository
import com.shakib.movieapp.data.dto.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UseCase(private val repository: Repository) {
    @NativeCoroutines
    fun getPopularMovies(page: Int) =
        flow { emit(repository.getPopularMovies(page)) }.flowOn(Dispatchers.Default)

    @NativeCoroutines
    fun getRecommendations(id: Int) =
        flow { emit(repository.getRecommendations(id.toString())) }.flowOn(Dispatchers.Default)

    fun getFavoriteMovies() = repository.getFavoriteMovies()

    fun addToFavorites(movie: Movie) = repository.addToFavorites(movie)

    fun removeFromFavorites(movie: Movie) = repository.removeFromFavorites(movie)
}

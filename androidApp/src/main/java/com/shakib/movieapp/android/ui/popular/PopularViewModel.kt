package com.shakib.movieapp.android.ui.popular

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.domain.UseCase
import kotlinx.coroutines.flow.Flow

class PopularViewModel(private val useCase: UseCase) : ViewModel() {

    var favoriteList = mutableStateListOf<Movie>()

    val movieList: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 1)) {
        MoviePagingSource(useCase)
    }.flow.cachedIn(viewModelScope)

    init {
        fetchFavoriteMovies()
    }

    private fun fetchFavoriteMovies() {
        favoriteList.addAll(useCase.getFavoriteMovies())
    }

    fun addToFavorite(movie: Movie) {
        useCase.addToFavorites(movie)
        favoriteList.add(movie)
    }

    fun removeFromFavorite(movie: Movie) {
        useCase.removeFromFavorites(movie)
        favoriteList.remove(movie)
    }

    fun checkFavorite(movie: Movie) = favoriteList.contains(movie)

}
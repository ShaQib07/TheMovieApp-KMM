package com.shakib.movieapp.android.ui.detail

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.domain.UseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val useCase: UseCase) : ViewModel() {

    var movieList = mutableStateListOf<Movie>()

    fun getRecommendations(id: Int) {
        viewModelScope.launch {
            useCase.getRecommendations(id).collect {
                it.payload?.results?.let { recommendations ->
                    movieList.addAll(recommendations)
                }
            }
        }
    }
}
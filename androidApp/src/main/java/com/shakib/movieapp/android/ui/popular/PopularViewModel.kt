package com.shakib.movieapp.android.ui.popular

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

    val movieList: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 1)) {
        MoviePagingSource(useCase)
    }.flow.cachedIn(viewModelScope)
}
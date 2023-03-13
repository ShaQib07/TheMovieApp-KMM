package com.shakib.movieapp.domain

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.shakib.movieapp.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UseCase(private val repository: Repository) {
    @NativeCoroutines
    fun getPopularMovies(page: Int) =
        flow { emit(repository.getPopularMovies(page)) }.flowOn(Dispatchers.Default)
}

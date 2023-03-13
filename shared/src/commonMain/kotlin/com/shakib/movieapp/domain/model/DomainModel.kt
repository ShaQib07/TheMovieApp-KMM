package com.shakib.movieapp.domain.model

data class DomainModel<T>(
    var payload: T? = null,
    var error: Exception? = null,
    var isSuccess: Boolean = false
)

package com.shakib.movieapp.android.ui.detail

import android.os.Parcelable
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.data.dto.getBackdrop
import com.shakib.movieapp.data.dto.getPoster
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUI(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable

fun Movie.map() = MovieUI(
    id = this.id,
    title = this.title,
    original_title = this.original_title,
    overview = this.overview,
    backdrop_path = this.getBackdrop(),
    poster_path = this.getPoster(),
    original_language = this.original_language,
    popularity = this.popularity,
    release_date = this.release_date,
    vote_average = this.vote_average,
    vote_count = this.vote_count
)
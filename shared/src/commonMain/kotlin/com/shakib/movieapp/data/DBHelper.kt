package com.shakib.movieapp.data

import com.shakib.movieapp.MovieDb
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.data.dto.map
import com.squareup.sqldelight.db.SqlDriver

class DBHelper(sqlDriver: SqlDriver) {
    private val dbRef = MovieDb(sqlDriver)

    fun fetchAllMovies() =
        dbRef.tableQueries
            .selectAll()
            .executeAsList().map { it.map() }

    fun insertMovie(movie: Movie) {
        dbRef.tableQueries.insertMovie(
            id = movie.id.toLong(),
            title = movie.title,
            original_title = movie.original_title,
            overview = movie.overview,
            backdrop_path = movie.backdrop_path,
            poster_path = movie.poster_path,
            original_language = movie.original_language,
            popularity = movie.popularity.toString(),
            release_date = movie.release_date,
            vote_average = movie.vote_average.toString(),
            vote_count = movie.vote_count.toLong()
        )
    }

    fun deleteMovie(movie: Movie) {
        dbRef.tableQueries.deleteMovie(movie.id.toLong())
    }
}

package com.shakib.movieapp.data.dto

@kotlinx.serialization.Serializable
data class Movie(
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
) {
    constructor() : this("/22z44LPkMyf5nyyXvv8qQLsbom.jpg",
        631842,
        "en",
        "Knock at the Cabin",
        "While vacationing at a remote cabin, a young girl and her two fathers are taken hostage by four armed strangers who demand that the family make an unthinkable choice to avert the apocalypse. With limited access to the outside world, the family must decide what they believe before all is lost.",
        3975.228,
        "/dm06L9pxDOL9jNSK4Cb6y139rrG.jpg",
        "2023-02-01",
        "Knock at the Cabin",
        6.6,
        826)
}

fun Movie.getBackdrop() = "https://image.tmdb.org/t/p/w500/$backdrop_path"
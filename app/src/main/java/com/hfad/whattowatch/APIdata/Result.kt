package com.hfad.whattowatch.APIdata

data class Result(
    val cast: List<String>,
    val creators: List<String>,
    val directors: List<String>,
    val episodeCount: Int,
    val firstAirYear: Int,
    val genres: List<Genre>,
    val imdbId: String,
    val lastAirYear: Int,
    val originalTitle: String,
    val overview: String,
    val seasonCount: Int,
    val seasons: List<Season>,
    val status: Status,
    val streamingInfo: StreamingInfoXX,
    val title: String,
    val tmdbId: Int,
    val type: String,
    val year: Int
)
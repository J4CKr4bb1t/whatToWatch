package com.hfad.whattowatch.API

data class Season(
    val episodes: List<Episode>,
    val firstAirYear: Int,
    val lastAirYear: Int,
    val streamingInfo: StreamingInfoX,
    val title: String,
    val type: String
)
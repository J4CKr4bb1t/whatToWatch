package com.hfad.whattowatch.API

data class Episode(
    val streamingInfo: StreamingInfo,
    val title: String,
    val type: String,
    val year: Int
)
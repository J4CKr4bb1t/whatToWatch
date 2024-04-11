package com.hfad.whattowatch.APIdata

data class Episode(
    val streamingInfo: StreamingInfo,
    val title: String,
    val type: String,
    val year: Int
)
package com.hfad.whattowatch.API

data class U(
    val addon: String,
    val audios: List<Audio>,
    val availableSince: Int,
    val leaving: Int,
    val link: String,
    val price: Price,
    val quality: String,
    val service: String,
    val streamingType: String,
    val subtitles: List<Subtitle>,
    val videoLink: String
)
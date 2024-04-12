package com.hfad.whattowatch.API

data class UXX(
    val addon: String,
    val audios: List<Audio>,
    val availableSince: Int,
    val leaving: Int,
    val link: String,
    val price: Price,
    val quality: String,
    val service: String,
    val streamingType: String,
    val subtitles: List<SubtitleXX>,
    val videoLink: String
)
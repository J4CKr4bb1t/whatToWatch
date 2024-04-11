package com.hfad.whattowatch.APIdata

import com.hfad.whattowatch.APIdata.Audio
import com.hfad.whattowatch.APIdata.SubtitleX

data class UX(
    val audios: List<Audio>,
    val availableSince: Int,
    val link: String,
    val quality: String,
    val service: String,
    val streamingType: String,
    val subtitles: List<SubtitleX>,
    val videoLink: String
)
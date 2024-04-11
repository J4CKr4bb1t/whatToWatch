package com.hfad.whattowatch.APIdata

import com.hfad.whattowatch.APIdata.Audio
import com.hfad.whattowatch.APIdata.Subtitle

data class U(
    val audios: List<Audio>,
    val availableSince: Int,
    val link: String,
    val quality: String,
    val service: String,
    val streamingType: String,
    val subtitles: List<Subtitle>,
    val videoLink: String
)
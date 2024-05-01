package com.hfad.whattowatch.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class Media (

    //tmdb number
    @PrimaryKey(autoGenerate = false)
    var mediaTMDB: Long = 0L,

    @ColumnInfo(name = "media_type")
    var mediaType: String = "",

    @ColumnInfo(name = "media_title")
    var mediaTitle: String = "",

    @ColumnInfo(name = "media_desc")
    var mediaDesc: String = "",

    @ColumnInfo(name = "media_year")
    var mediaDesc: Int = 0,

    @ColumnInfo(name = "media_fav")
    var mediaFavorited: Boolean = false
)
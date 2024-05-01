package com.hfad.whattowatch.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hfad.whattowatch.API.StreamingInfo

@Entity(tableName = "media_table")
data class Media(
    //ALL VALUES STORED
    /*
    * TMDB : Long
    * TITLE: String
    * TYPE: String
    * YEAR: int
    * GENRE: String
    * DESC: String
    * STREAMINGINFO: streamingInfo?
    * FAVORITED: Boolean
    * */


    //tmdb number
    @PrimaryKey(autoGenerate = false)
    var mediaTMDB: Long = 0L,

    //title
    @ColumnInfo(name = "media_title")
    var mediaTitle: String = "",

    //type
    @ColumnInfo(name = "media_type")
    var mediaType: String = "",

    //year
    @ColumnInfo(name = "media_year")
    var mediaYear: Int = 0,

    //genre
    @ColumnInfo(name = "media_genre")
    var mediaGenre: String = "",

    //description / overview
    @ColumnInfo(name = "media_desc")
    var mediaDesc: String = "",

    //streamingInfo for movie
    @ColumnInfo(name = "media_stream")
    var mediaStreaming: StreamingInfo? = null,

    //is it favorited?
    @ColumnInfo(name = "media_fav")
    var mediaFavorited: Boolean = false
)
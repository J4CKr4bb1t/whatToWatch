package com.hfad.whattowatch.favorites

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.whattowatch.MediaDao
import kotlinx.coroutines.launch

class MediaViewModel (val dao: MediaDao) : ViewModel() {
    /*DATABASE VALUES
   * TMDB : Long
   * TITLE: String
   * TYPE: String
   * YEAR: int
   * GENRE: String
   * DESC: String
   * STREAMINGINFO: streamingInfo?
   * FAVORITED: Boolean
   * */
    var newMediaTMDB = 555555554
    var newMediaTitle = "default Title"
    var newMediaType = "default type"
    var newMediaYear = 1999
    var newMediaGenre = "default genre"
    var newMediaDesc = "default desc"
    var newMediaStreaming = null
    var newMediaFavorited = true


    fun addMedia() {
        viewModelScope.launch {
            val media = Media()
            media.mediaTMDB = newMediaTMDB.toLong()
            media.mediaTitle = newMediaTitle
            media.mediaType = newMediaType
            media.mediaYear = newMediaYear
            media.mediaGenre = newMediaGenre
            media.mediaDesc = newMediaDesc
            //media.mediaStreaming = newMediaStreaming
            media.mediaFavorited = newMediaFavorited

            Log.d("INSERTING" , "making dao.insert() call!!")
            dao.insert(media)

        }
    }

    fun deleteMedia(TMDB: Long)
    {
        viewModelScope.launch {
            Log.d("deleting" , "making dao.insert() call!!")
            val media = dao.get(TMDB).value
            if (media != null) {
                dao.delete(media)
            }
        }
    }

}
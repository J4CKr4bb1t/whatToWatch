package com.hfad.whattowatch.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.whattowatch.MediaDao
import kotlinx.coroutines.launch

class MediaViewModel (val dao: MediaDao) : ViewModel() {
    var newMediaTitle = "defaultTitle"


    //as of now, add Task JUST adds the title. Once that's working, we can add more info
    fun addTask() {
        viewModelScope.launch {
            val media = Media(newMediaTitle)
            //media.mediaTitle = newMediaTitle
            dao.insert(media)
        }
    }
}
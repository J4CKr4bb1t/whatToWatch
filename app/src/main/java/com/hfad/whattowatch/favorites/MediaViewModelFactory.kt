package com.hfad.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.whattowatch.MediaDao
import com.hfad.whattowatch.favorites.MediaViewModel

class TasksViewModelFactory (private val dao: MediaDao) : ViewModelProvider.Factory {

    //override -
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MediaViewModel::class.java)) {
            return MediaViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
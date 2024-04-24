package com.hfad.whattowatch

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//need Media import because rest of the database stuff is in a folder
import com.hfad.whattowatch.favorites.Media

@Dao
interface MediaDao {

    //without suspend, they would NOT run in the background
    //this can block other methods cuz databases can be slow sometimes
    @Insert
    suspend fun insert(media: Media)

    @Update
    suspend fun update(media: Media)

    @Delete
    suspend fun delete(media: Media)

    //these use live data, so they do not need to be suspended. they ALREADY run in the background
    @Query("SELECT * FROM media_table WHERE mediaTMDB = :mediaTMDB")
    fun get(mediaTMBD: Long): LiveData<Media>

    @Query("SELECT * FROM media_table ORDER BY mediaTMDB DESC")
    fun getAll(): LiveData<List<Media>>

}
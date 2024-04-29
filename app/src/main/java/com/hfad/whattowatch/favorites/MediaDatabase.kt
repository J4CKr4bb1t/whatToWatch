package com.hfad.whattowatch.favorites

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//import because folder moment
import com.hfad.whattowatch.MediaDao

@Database(entities = [Media::class], version = 1, exportSchema = false)
abstract class MediaDatabase : RoomDatabase() {
    abstract val mediaDao: MediaDao

    companion object {
        @Volatile
        private var INSTANCE: MediaDatabase? = null

        fun getInstance(context: Context): MediaDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MediaDatabase::class.java,
                        "media_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
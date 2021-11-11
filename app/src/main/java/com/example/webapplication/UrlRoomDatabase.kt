package com.example.webapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ALink::class), version = 1, exportSchema = false)
public abstract class UrlRoomDatabase : RoomDatabase(){
    abstract fun urlDao(): UrlDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UrlRoomDatabase? = null

        fun getDatabase(context: Context): UrlRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UrlRoomDatabase::class.java,
                    "bookmark_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}
package com.example.lab4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab4.model.She

@Database(entities = [She::class], version = 1)
abstract class SheDatabase: RoomDatabase() {
    abstract fun getSheDao(): SheDao
    companion object{
        @Volatile
        private var instance: SheDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SheDatabase::class.java,
                "she_db"
            ).build()
    }
}


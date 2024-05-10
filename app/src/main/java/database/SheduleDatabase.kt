package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.Shedule

@Database(entities = [Shedule::class], version = 1)
abstract class SheduleDatabase: RoomDatabase() {
    abstract fun getSheduleDao(): SheduleDao
    companion object{
        @Volatile
        private var instance: SheduleDatabase? = null
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
                SheduleDatabase::class.java,
                "shedule_db"
            ).build()
    }
}
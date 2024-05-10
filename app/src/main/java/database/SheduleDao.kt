package database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import model.Shedule

@Dao
interface SheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShedule(shedule: Shedule)

    @Update
    suspend fun updateShedule(shedule: Shedule)

    @Delete
    suspend fun deleteShedule(shedule: Shedule)

    @Query("SELECT * FROM SHEDULES ORDER BY id DESC")
    fun getALlShedules(): LiveData<List<Shedule>>

    @Query("SELECT * FROM SHEDULES WHERE sheTitle LIKE :query OR sheDesc LIKE :query")
    fun searchShedule(query: String?): LiveData<List<Shedule>>


}
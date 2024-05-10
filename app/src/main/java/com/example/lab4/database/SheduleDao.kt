package com.example.lab4.database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lab4.model.She

@Dao
interface SheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShe(she: She)

    @Update
    suspend fun updateShe(she: She)

    @Delete
    suspend fun deleteShe(she: She)

    @Query("SELECT * FROM SHES ORDER BY id DESC")
    fun getALlShes(): LiveData<List<She>>

    @Query("SELECT * FROM SHES WHERE sheTitle LIKE :query OR sheDesc LIKE :query")
    fun searchShe(query: String?): LiveData<List<She>>


}
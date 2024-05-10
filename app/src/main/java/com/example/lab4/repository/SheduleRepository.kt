package com.example.lab4.repository

import com.example.lab4.database.SheDatabase
import com.example.lab4.model.She

class SheRepository(private val db: SheDatabase) {
    suspend fun insertShe(she: She) = db.getSheDao().insertShe(she)
    suspend fun deleteShe(she: She) = db.getSheDao().deleteShe(she)
    suspend fun updateShe(she: She) = db.getSheDao().updateShe(she)

    fun getAllShes() = db.getSheDao().getALlShes()
    fun searchShe(query: String?) = db.getSheDao().searchShe(query)
}
package repository

import database.SheduleDatabase
import model.Shedule

class SheduleRepository(private val db: SheduleDatabase) {
    suspend fun insertShedule(shedule: Shedule) = db.getSheduleDao().insertShedule(shedule)
    suspend fun deleteShedule(shedule: Shedule) = db.getSheduleDao().deleteShedule(shedule)
    suspend fun updateShedule(shedule: Shedule) = db.getSheduleDao().updateShedule(shedule)

    fun getAllShedules() = db.getSheduleDao().getALlShedules()
    fun searchShedule(query: String?) = db.getSheduleDao().searchShedule(query)
}
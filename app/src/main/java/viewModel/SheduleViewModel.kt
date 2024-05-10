package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Shedule
import repository.SheduleRepository

class SheduleViewModel(app: Application, private val sheduleRepository: SheduleRepository):AndroidViewModel(app) {
    fun addShedule(shedule: Shedule) =
        viewModelScope.launch {
            sheduleRepository.insertShedule(shedule)
        }

    fun updateShedule(shedule: Shedule) =
        viewModelScope.launch {
            sheduleRepository.updateShedule(shedule)
        }

    fun deleteShedule(shedule: Shedule) =
        viewModelScope.launch {
            sheduleRepository.deleteShedule(shedule)
        }

    fun getAllShedules() = sheduleRepository.getAllShedules()

    fun searchShedule(query: String?) =
        sheduleRepository.searchShedule(query)
}
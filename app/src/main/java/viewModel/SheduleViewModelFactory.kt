package viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.SheduleRepository

class SheduleViewModelFactory (val app: Application, private val sheduleRepository: SheduleRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SheduleViewModel(app, sheduleRepository) as T
    }
}
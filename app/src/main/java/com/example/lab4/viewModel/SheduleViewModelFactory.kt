package com.example.lab4.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab4.repository.SheRepository

class SheViewModelFactory (val app: Application, private val sheRepository: SheRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SheViewModel(app, sheRepository) as T
    }
}
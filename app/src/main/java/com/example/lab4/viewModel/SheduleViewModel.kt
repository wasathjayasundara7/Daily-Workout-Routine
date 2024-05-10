package com.example.lab4.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.lab4.model.She
import com.example.lab4.repository.SheRepository

class SheViewModel(app: Application, private val sheRepository: SheRepository):AndroidViewModel(app) {
    fun add(she: She) =
        viewModelScope.launch {
            sheRepository.insertShe(she)
        }

    fun updateShe(she: She) =
        viewModelScope.launch {
            sheRepository.updateShe(she)
        }

    fun deleteShe(she: She) =
        viewModelScope.launch {
            sheRepository.deleteShe(she)
        }

    fun getAllShes() = sheRepository.getAllShes()

    fun searchShe(query: String?) =
        sheRepository.searchShe(query)
}
package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import database.SheduleDatabase
import repository.SheduleRepository
import viewModel.SheduleViewModel
import viewModel.SheduleViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var sheduleViewModel: SheduleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel(){
        val sheduleRepository = SheduleRepository(SheduleDatabase(this))
        val viewModelProviderFactory = SheduleViewModelFactory(application, sheduleRepository)
        sheduleViewModel = ViewModelProvider(this, viewModelProviderFactory)[SheduleViewModel::class.java]

    }
}
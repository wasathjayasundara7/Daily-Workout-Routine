package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lab4.database.SheDatabase
import com.example.lab4.repository.SheRepository
import com.example.lab4.viewModel.SheViewModel
import com.example.lab4.viewModel.SheViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var sheViewModel: SheViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel(){
        val sheRepository = SheRepository(SheDatabase(this))
        val viewModelProviderFactory = SheViewModelFactory(application, sheRepository)
        sheViewModel = ViewModelProvider(this, viewModelProviderFactory)[SheViewModel::class.java]

    }
}
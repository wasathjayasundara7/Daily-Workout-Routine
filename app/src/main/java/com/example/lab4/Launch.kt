package com.example.lab4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Launch: AppCompatActivity()  {
    lateinit var logo : ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch)
        logo = findViewById(R.id.imageView2)
    }
    fun touchLayout(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
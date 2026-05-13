package com.example.myapplication55

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import android.content.Intent
import android.widget.Button

class Intro1_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro1)

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            startActivity(Intent(this, Intro2_Activity::class.java))
            finish()
        }
    }
}
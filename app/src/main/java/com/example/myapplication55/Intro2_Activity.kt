package com.example.myapplication55

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import android.content.Intent
import android.widget.Button

class Intro2_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro2)

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            startActivity(Intent(this, Intro3_Activity::class.java))
            finish()
        }
    }
}
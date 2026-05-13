package com.example.myapplication55

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.widget.Button

class Intro3_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro3)

        findViewById<Button>(R.id.getStartedButton).setOnClickListener {
            // save that intro has been seen
            getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .putBoolean("intro_done", true)
                .apply()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
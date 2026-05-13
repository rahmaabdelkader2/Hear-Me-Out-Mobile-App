package com.example.myapplication55

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val childButton = findViewById<LinearLayout>(R.id.childButton)
        val adultButton = findViewById<LinearLayout>(R.id.adultButton)
        val voiceSwitch = findViewById<Switch>(R.id.voiceSwitch)
        val maleLabel = findViewById<TextView>(R.id.maleLabel)
        val femaleLabel = findViewById<TextView>(R.id.femaleLabel)

        // load saved voice preference and set switch accordingly
        val isFemale = getSharedPreferences("tts_prefs", MODE_PRIVATE)
            .getBoolean("is_female", false)
        voiceSwitch.isChecked = isFemale

        if (isFemale) {
            femaleLabel.setTextColor(getColor(android.R.color.holo_purple))
            maleLabel.setTextColor(android.graphics.Color.parseColor("#888888"))
        } else {
            maleLabel.setTextColor(android.graphics.Color.parseColor("#1565C0"))
            femaleLabel.setTextColor(android.graphics.Color.parseColor("#888888"))
        }

        voiceSwitch.setOnCheckedChangeListener { _, isChecked ->
            ElevenLabsTTS.setVoice(this, isChecked)
            if (isChecked) {
                femaleLabel.setTextColor(getColor(android.R.color.holo_purple))
                maleLabel.setTextColor(android.graphics.Color.parseColor("#888888"))
            } else {
                maleLabel.setTextColor(android.graphics.Color.parseColor("#1565C0"))
                femaleLabel.setTextColor(android.graphics.Color.parseColor("#888888"))
            }
        }

        childButton.setOnClickListener {
            startActivity(Intent(this, ChildActivity::class.java))
        }

        adultButton.setOnClickListener {
            startActivity(Intent(this, AdultActivity::class.java))
        }


        // check if first time
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        if (!prefs.getBoolean("intro_done", false)) {
            startActivity(Intent(this, Intro1_Activity::class.java))
            finish()
            return
        }
    }
}
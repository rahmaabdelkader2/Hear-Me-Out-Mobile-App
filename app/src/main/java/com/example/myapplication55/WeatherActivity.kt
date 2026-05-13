package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val wordList = listOf(
            WordItem("حَرّ", "Hot", R.drawable.hot),
            WordItem("بَرْد", "Cold", R.drawable.cold),
            WordItem("شَمْس", "Sunny", R.drawable.sunny),
            WordItem("مَطَر", "Rainy", R.drawable.rainy),
            WordItem("رِيح", "Windy", R.drawable.windy),
            WordItem("غُيُوم", "Cloudy", R.drawable.cloudy)
        )

        findViewById<ImageButton>(R.id.backButton).setOnClickListener { finish() }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
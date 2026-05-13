package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ColorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val wordList = listOf(
            WordItem("أَحْمَر", "Red", R.drawable.red),
            WordItem("أَزْرَق", "Blue", R.drawable.blue),
            WordItem("أَخْضَر", "Green", R.drawable.green),
            WordItem("أَصْفَر", "Yellow", R.drawable.yellow),
            WordItem("أَبْيَض", "White", R.drawable.white),
            WordItem("إِسْوِد", "Black", R.drawable.black),
            WordItem("بَنَفْسِجِي", "Purple", R.drawable.purple)
        )

        findViewById<ImageButton>(R.id.backButton).setOnClickListener { finish() }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
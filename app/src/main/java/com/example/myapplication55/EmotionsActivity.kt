package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmotionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val wordList = listOf(
            WordItem("سَعِيد", "Happy", R.drawable.happy),
            WordItem("زَعْلَان", "Sad", R.drawable.sad),
            WordItem("غَضْبَان", "Angry", R.drawable.angry),
            WordItem("خَايِف", "Scared", R.drawable.scared),
            WordItem("تَعْبَان", "Tired", R.drawable.tired),
            WordItem("نَعْسَان", "Sleepy", R.drawable.sleepy)
        )

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
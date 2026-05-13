package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val wordList = listOf(
            WordItem("آكَل", "Eat", R.drawable.eat),
            WordItem("يِشْرَب", "Drink", R.drawable.drink),
            WordItem("يِنَام", "Sleep", R.drawable.sleep),
            WordItem("يُوقَف", "Stop", R.drawable.stop),
            WordItem("يِمْشِي", "Walk", R.drawable.walk),
            WordItem("يِنُطّ", "Jump", R.drawable.jump)
        )

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
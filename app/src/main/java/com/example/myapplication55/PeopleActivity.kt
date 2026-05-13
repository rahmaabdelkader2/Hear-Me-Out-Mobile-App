package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PeopleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val wordList = listOf(
            WordItem("مَامَا", "Mum", R.drawable.mum),
            WordItem("بَابَا", "Dad", R.drawable.dad),
            WordItem("أَخ", "Brother", R.drawable.brother),
            WordItem("أُخْت", "Sister", R.drawable.sister),
            WordItem("جَدُّو", "Grandpa", R.drawable.grandpa),
            WordItem("تِيتَا", "Grandma", R.drawable.grandma),
            WordItem("دُكْتُور", "Doctor", R.drawable.doctor),
            WordItem("مُدَرِّس", "Teacher", R.drawable.teacher),
            WordItem("صَاحِب", "Friend", R.drawable.friend)
        )

        findViewById<ImageButton>(R.id.backButton).setOnClickListener { finish() }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
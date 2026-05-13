package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        val categories = listOf(
            CategoryItem("Food", "أَكْل", R.drawable.food),
            CategoryItem("Emotions", "مَشَاعِر", R.drawable.emotions),
            CategoryItem("Actions", "أَفْعَال", R.drawable.actions),
            CategoryItem("Places", "أَمَاكِن", R.drawable.places),
            CategoryItem("People", "نَاس", R.drawable.people),
            CategoryItem("Colors", "أَلْوَان", R.drawable.colors),
            CategoryItem("Numbers", "أَرْقَام", R.drawable.numbers),
            CategoryItem("Weather", "الجَوّ", R.drawable.weather)
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CategoryAdapter(categories, this)

        backButton.setOnClickListener {
            finish()
        }
    }
}
package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val category = intent.getStringExtra("category")

        val wordList = listOf(
            WordItem("تُفَّاحَة", "Apple", R.drawable.apple),
            WordItem("عَيْش", "Bread", R.drawable.bread),
            WordItem("مَوْز", "Banana", R.drawable.banana),
            WordItem("لَبَن", "Milk", R.drawable.milk),
            WordItem("فِرَاخ", "Chicken", R.drawable.chiken),
            WordItem("بَيْض", "Egg", R.drawable.egg),
            WordItem("جِبْنَة", "Cheese", R.drawable.cheese),
            WordItem("بِيتْزَا", "Pizza", R.drawable.pizza)
        )



        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
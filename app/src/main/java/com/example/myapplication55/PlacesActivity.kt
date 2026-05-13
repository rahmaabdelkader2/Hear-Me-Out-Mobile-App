package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val wordList = listOf(
            WordItem("بَيْت", "Home", R.drawable.home),
            WordItem("مَدْرَسَة", "School", R.drawable.school),
            WordItem("مُسْتَشْفَى", "Hospital", R.drawable.hospital),
            WordItem("مَسْجِد", "Mosque", R.drawable.mosque),
            WordItem("حَمَّام", "Bathroom", R.drawable.bathroom),
            WordItem("مَطْبَخ", "Kitchen", R.drawable.kitchen),
            WordItem("جِنَيْنَة", "Park", R.drawable.park),
            WordItem("مَحَل", "Shop", R.drawable.shop)
        )

        findViewById<ImageButton>(R.id.backButton).setOnClickListener { finish() }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
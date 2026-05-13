package com.example.myapplication55

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NumbersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val wordList = listOf(
            WordItem("واحد", "One", R.drawable.one),
            WordItem("اتنين", "Two", R.drawable.two),
            WordItem("تلاتة", "Three", R.drawable.three),
            WordItem("اربعة", "Four", R.drawable.four),
            WordItem("خمسة", "Five", R.drawable.five),
            WordItem("ستة", "Six", R.drawable.six),
            WordItem("سبعة", "Seven", R.drawable.seven),
            WordItem("تمانية", "Eight", R.drawable.eight),
            WordItem("تسعة", "Nine", R.drawable.nine),
            WordItem("عشرة", "Ten", R.drawable.ten)
        )

        findViewById<ImageButton>(R.id.backButton).setOnClickListener { finish() }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(wordList, this)
    }
}
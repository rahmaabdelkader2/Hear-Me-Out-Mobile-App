package com.example.myapplication55

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val list: List<CategoryItem>,
    private val context: Context
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.categoryImage)
        val text: TextView = view.findViewById(R.id.categoryText)
        val textArabic: TextView = view.findViewById(R.id.categoryTextArabic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.text.text = item.name
        holder.textArabic.text = item.nameArabic
        holder.image.setImageResource(item.imageRes)

        holder.itemView.setOnClickListener {
            val intent = when (item.name) {
                "Food" -> Intent(context, FoodActivity::class.java)
                "Emotions" -> Intent(context, EmotionsActivity::class.java)
                "Actions" -> Intent(context, ActionsActivity::class.java)
                "People" -> Intent(context, PeopleActivity::class.java)
                "Places" -> Intent(context, PlacesActivity::class.java)
                "Colors" -> Intent(context, ColorsActivity::class.java)
                "Weather" -> Intent(context, WeatherActivity::class.java)
                "Numbers" -> Intent(context, NumbersActivity::class.java)
                else -> Intent(context, FoodActivity::class.java)
            }
            context.startActivity(intent)
        }
    }
}
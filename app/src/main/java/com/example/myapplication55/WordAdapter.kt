package com.example.myapplication55

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(
    private val list: List<WordItem>,
    private val context: Context
) : RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val textArabic: TextView = view.findViewById(R.id.textArabic)
        val textEnglish: TextView = view.findViewById(R.id.textEnglish)
        val speakBtn: ImageButton = view.findViewById(R.id.speakBtn)
        val speakEnBtn: ImageButton = view.findViewById(R.id.speakEnBtn)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.textArabic.text = item.textArabic
        holder.textEnglish.text = item.textEnglish
        holder.image.setImageResource(item.imageRes)

        // Arabic speaker - blue button
        holder.speakBtn.setOnClickListener {
            ElevenLabsTTS.speak(context, item.textArabic)
        }

        // English speaker - green button
        holder.speakEnBtn.setOnClickListener {
            ElevenLabsTTS.speak(context, item.textEnglish)
        }
    }
}
package com.example.myapplication55

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdultActivity : AppCompatActivity() {

    private lateinit var inputText: AutoCompleteTextView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var db: AppDatabase
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adult)

        val speakButton = findViewById<Button>(R.id.speakButton)
        inputText = findViewById(R.id.inputText)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val handwritingButton = findViewById<Button>(R.id.handwritingButton)

        db = AppDatabase.get(this)

        // seed default words on first launch
        CoroutineScope(Dispatchers.IO).launch {
            seedDefaultWords()
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, mutableListOf())
        inputText.setAdapter(adapter)
        inputText.threshold = 1

        inputText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                val query = s?.toString()?.trim() ?: return
                if (query.isEmpty()) return

                searchJob?.cancel()
                searchJob = CoroutineScope(Dispatchers.IO).launch {
                    val results = db.wordDao().search(query)
                    withContext(Dispatchers.Main) {
                        adapter.clear()
                        adapter.addAll(results)
                        adapter.notifyDataSetChanged()
                        if (results.isNotEmpty()) {
                            inputText.showDropDown()
                        }
                    }
                }
            }
        })

        speakButton.setOnClickListener {
            val text = inputText.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // save to database
            CoroutineScope(Dispatchers.IO).launch {
                val words = text.split(" ").filter { it.length > 1 }
                words.forEach { word ->
                    val existing = db.wordDao().findWord(word)
                    if (existing != null) {
                        db.wordDao().incrementFrequency(word)
                    } else {
                        db.wordDao().insert(WordEntity(word = word))
                    }
                }
                val existing = db.wordDao().findWord(text)
                if (existing != null) {
                    db.wordDao().incrementFrequency(text)
                } else {
                    db.wordDao().insert(WordEntity(word = text))
                }
            }

            ElevenLabsTTS.speak(this, text)
        }

        backButton.setOnClickListener { finish() }

        handwritingButton.setOnClickListener {
            startActivity(Intent(this, HandwritingActivity::class.java))
        }
    }


    private fun seedDefaultWords() {
        val defaults = listOf(

            // ===== الاحتياجات الأساسية =====
            "أَنَا عَايِز", "أَنَا مُحْتَاجَة", "أَنَا مُحْتَاج", "مِن فَضْلَك", "شُكْرًا",
            "آيْوَه", "لَأ", "مُمْكِن", "مِش مُمْكِن", "مِش عَارِف",
            "I want", "I need", "Please", "Thank you", "Yes", "No",
            "I don't know", "Help me", "Stop", "Wait",

            // ===== الأكل والشرب =====
            "أَنَا جَعَان", "أَنَا عَطْشَان", "عَايِز آكُل", "عَايِز أَشْرَب",
            "عَايِز مَيَّه", "عَايِز عَصِير", "عَايِز لَبَن", "عَايِز شَاي",
            "عَايِز آكُل فِرَاخ", "عَايِز آكُل عَيْش", "عَايِز آكُل خُضَار",
            "عَايِز آكُل فَاكِهَة", "عَايِز حَاجَة حِلْوَة", "الأَكْل بَارِد",
            "الأَكْل سُخْن", "مِش عَايِز آكُل", "خَلَّصْت الأَكْل",
            "I am hungry", "I am thirsty", "I want water",
            "I want juice", "I want milk", "I want food",
            "I want something sweet", "The food is cold", "The food is hot",
            "I finished eating", "I don't want to eat",

            // ===== المشاعر والأحاسيس =====
            "أَنَا كْوَيِّس", "أَنَا مِش كْوَيِّس", "أَنَا زَعْلَان", "أَنَا سَعِيد",
            "أَنَا خَايِف", "أَنَا تَعْبَان", "أَنَا مَبْسُوط", "أَنَا مُش لَاقِي",
            "أَنَا بَحِبَّك", "أَنَا وِحْدَانِي", "أَنَا قَلْقَان", "أَنَا مَبْهَدَل",
            "أَنَا فَرْحَان", "أَنَا زَهَقْت", "أَنَا مِش مُرْتَاح",
            "I am fine", "I am not okay", "I am sad", "I am happy",
            "I am scared", "I am tired", "I am angry", "I am bored",
            "I am lonely", "I am anxious", "I feel sick", "I am comfortable",
            "I am uncomfortable", "I love you",

            // ===== الألم والصحة =====
            "عِنْدِي وَجَع", "رَاسِي بِيُوجَعْنِي", "بَطْنِي بِيُوجَعْنِي",
            "ضَهْرِي بِيُوجَعْنِي", "رِجْلِي بِتُوجَعْنِي", "إِيدِي بِتُوجَعْنِي",
            "عِينِي بِتُوجَعْنِي", "سِنَانِي بِيُوجَعْنِي", "زُورِي بِيُوجَعْنِي",
            "أَنَا تَعْبَان أُوِي", "مُحْتَاج دُكْتُور", "مُحْتَاج دَوَا",
            "مُحْتَاج إِسْعَاف", "مِش قَادِر أَتْنَفِّس", "عِنْدِي سُخُونِيَّة",
            "I have pain", "My head hurts", "My stomach hurts",
            "My back hurts", "My leg hurts", "My arm hurts",
            "I can't breathe", "I need a doctor", "I need medicine",
            "Call an ambulance", "I have a fever", "I feel dizzy",
            "I feel nauseous", "I need to rest",

            // ===== الروتين اليومي =====
            "عَايِز أَصْحَى", "عَايِز أَغْسِل وِشِّي", "عَايِز أَسْتَحِمّ",
            "عَايِز أَلْبِس", "عَايِز أَتْفَطَّر", "عَايِز أَتْغَدَّى",
            "عَايِز أَتْعَشَّى", "عَايِز أَنَام", "عَايِز آخُد قَيْلُولَة",
            "عَايِز أَعْمِل حَمَّام", "عَايِز أَغْسِل سِنَانِي", "عَايِز أَشْرَب دَوَايَا",
            "I want to wake up", "I want to wash my face",
            "I want to shower", "I want to get dressed",
            "I want breakfast", "I want lunch", "I want dinner",
            "I want to sleep", "I want to use the bathroom",
            "I want to brush my teeth", "I need to take my medicine",

            // ===== الأماكن والتنقل =====
            "عَايِز أَرُوح البَيْت", "عَايِز أَرُوح المُسْتَشْفَى",
            "عَايِز أَرُوح الحَمَّام", "عَايِز أَرُوح المَدْرَسَة",
            "عَايِز أَرُوح المَسْجِد", "عَايِز أُخْرُج بَرَّه",
            "عَايِز أَقْعُد هِنَا", "مِش عَايِز أَرُوح",
            "I want to go home", "I want to go to the hospital",
            "I want to go to the bathroom", "I want to go to school",
            "I want to go outside", "I want to stay here",
            "I don't want to go", "Take me home",

            // ===== التواصل =====
            "اتَّصِل بِيَّا", "كَلِّم مَامَا", "كَلِّم بَابَا", "كَلِّم الدُّكْتُور",
            "مِش فَاهِم", "قُولْهَا تَانِي", "بَطِّي شْوَيَّة", "فِهِمْت",
            "سِمِعْت", "مِش سَامِع", "مُمْكِن تِسَاعِدْنِي",
            "Call my mom", "Call my dad", "Call the doctor",
            "I don't understand", "Say it again", "Speak slowly",
            "I understand", "I can't hear", "Can you help me",
            "Write it down", "Show me",

            // ===== الأشخاص =====
            "مَامَا", "بَابَا", "أَخُويَا", "أُخْتِي", "جَدُّو", "تِيتَا",
            "الدُّكْتُور", "المُمَرِّضَة", "المُدَرِّس", "صَاحِبِي",
            "Mom", "Dad", "Brother", "Sister", "Grandpa", "Grandma",
            "Doctor", "Nurse", "Teacher", "Friend",

            // ===== الأنشطة والترفيه =====
            "عَايِز أَتْفَرَّج تِلِفِزْيُون", "عَايِز أَلْعَب", "عَايِز أَسْمَع مُوسِيقَى",
            "عَايِز أَقْرَا", "عَايِز أَمْشِي", "عَايِز أَتْكَلِّم",
            "I want to watch TV", "I want to play", "I want to listen to music",
            "I want to read", "I want to walk", "I want to talk",

            // ===== الحرارة والبيئة =====
            "أَنَا حَرَّان", "أَنَا بَرْدَان", "الجَوّ حَرّ", "الجَوّ بَرْد",
            "افْتَح الشُّبَّاك", "قَفِّل الشُّبَّاك", "افْتَح المُكَيِّف",
            "قَفِّل النُّور", "افْتَح النُّور",
            "I am hot", "I am cold", "The weather is hot", "The weather is cold",
            "Open the window", "Close the window", "Turn on the AC",
            "Turn off the lights", "Turn on the lights",

            // ===== العبارات الاجتماعية =====
            "صَبَاح الخَيْر", "مَسَا الخَيْر", "إِزَيَّك", "تَمَام",
            "مَعَ السَّلَامَة", "أَهْلًا", "يَلَّا", "خَلَاص",
            "Good morning", "Good evening", "How are you",
            "Goodbye", "Hello", "Okay", "Done",

            // ===== المدرسة =====
            "عَايِز أَكْتِب", "عَايِز دَفْتَر", "مُحْتَاج قَلَم",
            "مِش فَاهِم الدَّرْس", "خَلَّصْت الوَاجِب",
            "مُحْتَاج مُسَاعْدَة فِي الدِّرَاسَة",
            "I want to write", "I need a notebook", "I need a pen",
            "I don't understand the lesson", "I finished my homework",

            // ===== الطوارئ =====
            "النَّجْدَة", "فِي حَاجَة غَلَط", "مِش قَادِر أَتْحَرَّك",
            "وِقِعْت", "مُحْتَاج مُسَاعْدَة دِلْوَقْتِي",
            "Help", "Something is wrong", "I can't move",
            "I fell down", "I need help now", "Emergency"
        )

        defaults.forEach { word ->
            if (db.wordDao().findWord(word) == null) {
                db.wordDao().insert(WordEntity(word = word))
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        searchJob?.cancel()
    }
}
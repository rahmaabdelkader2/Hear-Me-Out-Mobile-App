package com.example.myapplication55

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT word FROM words WHERE word LIKE :query || '%' ORDER BY frequency DESC LIMIT 10")
    fun search(query: String): List<String>

    @Query("SELECT * FROM words WHERE word = :word LIMIT 1")
    fun findWord(word: String): WordEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: WordEntity)

    @Query("UPDATE words SET frequency = frequency + 1 WHERE word = :word")
    fun incrementFrequency(word: String)
}
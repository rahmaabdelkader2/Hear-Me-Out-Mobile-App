package com.example.myapplication55

import android.content.Context
import android.media.MediaPlayer
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.cert.X509Certificate

object ElevenLabsTTS {

    private const val API_KEY = "sk_c5406304a9b046195e178967e3fd9f051362f2777466bc1a"
    private const val MALE_VOICE_ID = "wxweiHvoC2r2jFM7mS8b"
    private const val FEMALE_VOICE_ID = "L10lEremDiJfPicq5CPh"
    private const val PREF_NAME = "tts_prefs"
    private const val PREF_KEY = "is_female"

    private val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    })

    private val sslContext = SSLContext.getInstance("SSL").apply {
        init(null, trustAllCerts, java.security.SecureRandom())
    }

    private val client = OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .build()

    fun setVoice(context: Context, isFemale: Boolean) {
        // save to SharedPreferences so it persists across activities
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(PREF_KEY, isFemale)
            .apply()
    }

    private fun getCurrentVoiceId(context: Context): String {
        val isFemale = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getBoolean(PREF_KEY, false)
        return if (isFemale) FEMALE_VOICE_ID else MALE_VOICE_ID
    }

    fun speak(context: Context, text: String) {
        val voiceId = getCurrentVoiceId(context)

        android.util.Log.d("ElevenLabsTTS", "Using voice: $voiceId")

        val json = """
            {
                "text": "$text",
                "model_id": "eleven_multilingual_v2",
                "voice_settings": {
                    "stability": 0.5,
                    "similarity_boost": 0.75
                }
            }
        """.trimIndent()

        val body = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("https://api.elevenlabs.io/v1/text-to-speech/$voiceId")
            .addHeader("xi-api-key", API_KEY)
            .addHeader("Accept", "audio/mpeg")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                android.util.Log.e("ElevenLabsTTS", "Failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                android.util.Log.d("ElevenLabsTTS", "Response code: ${response.code}")

                if (!response.isSuccessful) {
                    android.util.Log.e("ElevenLabsTTS", "Error: ${response.body?.string()}")
                    return
                }

                val audioBytes = response.body?.bytes() ?: return
                val tempFile = File(context.cacheDir, "tts_output.mp3")
                FileOutputStream(tempFile).use { it.write(audioBytes) }

                val mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(tempFile.absolutePath)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
        })
    }
}
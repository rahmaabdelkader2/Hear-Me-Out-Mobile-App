package com.example.myapplication55

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.digitalink.DigitalInkRecognition
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModel
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModelIdentifier
import com.google.mlkit.vision.digitalink.DigitalInkRecognizerOptions

class HandwritingActivity : AppCompatActivity() {

    private lateinit var drawingView: DrawingView
    private lateinit var recognizedText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handwriting)

        drawingView = findViewById(R.id.drawingCanvas)
        recognizedText = findViewById(R.id.recognizedText)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val speakButton = findViewById<Button>(R.id.speakButton)

        backButton.setOnClickListener { finish() }

        clearButton.setOnClickListener {
            drawingView.clear()
            recognizedText.text = "Recognized text will appear here..."
        }

        speakButton.setOnClickListener {
            val text = recognizedText.text.toString()
            if (text == "Recognized text will appear here..." || text.isEmpty()) {
                Toast.makeText(this, "Please write something first", Toast.LENGTH_SHORT).show()
            } else {
                ElevenLabsTTS.speak(this, text)
            }
        }

        drawingView.setOnTouchListener { _, event ->
            drawingView.onTouchEvent(event)
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                recognizeHandwriting()
            }
            true
        }
    }

    private fun recognizeHandwriting() {

        val modelIdentifier = try {
            DigitalInkRecognitionModelIdentifier.fromLanguageTag("ar")
        } catch (e: Exception) {
            Toast.makeText(this, "Arabic model not supported", Toast.LENGTH_SHORT).show()
            return
        }

        val model = DigitalInkRecognitionModel.builder(modelIdentifier!!).build()
        val remoteModelManager = com.google.mlkit.common.model.RemoteModelManager.getInstance()

        remoteModelManager.isModelDownloaded(model).addOnSuccessListener { isDownloaded ->

            if (!isDownloaded) {
                recognizedText.text = "Downloading model..."

                remoteModelManager.download(
                    model,
                    com.google.mlkit.common.model.DownloadConditions.Builder().build()
                )
                    .addOnSuccessListener { runRecognition(model) }
                    .addOnFailureListener {
                        recognizedText.text = "Download failed. Check internet."
                    }

            } else {
                runRecognition(model)
            }
        }
    }

    private fun runRecognition(model: DigitalInkRecognitionModel) {

        val recognizer = DigitalInkRecognition.getClient(
            DigitalInkRecognizerOptions.builder(model).build()
        )

        val ink = drawingView.getInk()

        val startTime = android.os.SystemClock.elapsedRealtime()

        recognizer.recognize(ink)
            .addOnSuccessListener { result ->

                val endTime = android.os.SystemClock.elapsedRealtime()
                val duration = endTime - startTime

                val text = result.candidates.getOrNull(0)?.text ?: "Could not recognize"

                // UI → only text
                recognizedText.text = text

                // LogCat → only time
                android.util.Log.d("INK_TIME", "Recognition took: ${duration}ms")
                android.util.Log.d("INK_TIME", "Text: $text")

                // Toast → only time
                Toast.makeText(
                    this,
                    "Time: ${duration}ms",
                    Toast.LENGTH_SHORT
                ).show()
            }

            .addOnFailureListener {
                android.util.Log.e("INK_TIME", "Recognition failed")
                recognizedText.text = "Recognition failed"
            }
    }
}
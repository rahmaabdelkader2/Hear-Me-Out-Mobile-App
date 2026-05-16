# 💬 Hear Me Out — Augmentative Communication Android App

An Arabic/English bilingual mobile application designed to help people with speech impairments communicate their daily needs effectively. Built for Android using Kotlin.

---

## 📖 About

**Hear Me Out** is an Augmentative and Alternative Communication (AAC) tool that provides two dedicated modes:

- **Child Mode** — Visual flashcard-based communication with audio output
- **Adult Mode** — Typed or handwritten input converted to natural AI speech

The app specifically targets Egyptian Arabic-speaking users, supporting both the Egyptian dialect and English, addressing a significant gap in existing AAC tools that focus almost exclusively on English.

---

## ✨ Features

### Child Mode
- Browse categories: Food, Emotions, Actions, People, Places, Colors, Numbers, Weather
- Flashcards with icons and words in both Arabic (Egyptian dialect) and English
- Two audio buttons per card — one for Arabic, one for English
- Egyptian dialect tashkeel (diacritics) for accurate pronunciation display

### Adult Mode
- Type any sentence and press Speak
- Freehand handwriting on screen converted to text via Google ML Kit
- AI-powered word and phrase suggestions that learn from usage frequency
- Suggestions database preloaded with 200+ common AAC phrases in Arabic and English

### General
- Male / Female voice toggle on the home screen
- Bilingual support: Egyptian Arabic dialect + English
- Intro screens shown only on first launch
- Lightweight — approximately 60MB total including full local database
- Works on any Android device

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Kotlin |
| Platform | Android (min SDK 24) |
| Text to Speech | ElevenLabs API (`eleven_multilingual_v2`) |
| HTTP Client | OkHttp3 |
| Handwriting Recognition | Google ML Kit Digital Ink Recognition |
| Local Database | Room (SQLite) |
| Voice Preference Storage | SharedPreferences |
| UI Components | RecyclerView, CardView, AutoCompleteTextView |

---

## 🏗️ Project Structure

```
app/
├── MainActivity.kt              # Home screen with mode selection and voice toggle
├── ChildActivity.kt             # Category list for child mode
├── AdultActivity.kt             # Typing mode with word suggestions
├── HandwritingActivity.kt       # Freehand drawing to speech
├── CategoryAdapter.kt           # RecyclerView adapter for categories
├── WordAdapter.kt               # RecyclerView adapter for flashcards
├── ElevenLabsTTS.kt             # TTS API handler
├── DrawingView.kt               # Custom canvas for handwriting input
├── AppDatabase.kt               # Room database instance
├── WordDao.kt                   # Database queries
├── WordEntity.kt                # Database entity
├── GeminiSuggestions.kt         # AI suggestion handler (optional)
├── Intro1_Activity.kt           # Onboarding screen 1
├── Intro2_Activity.kt           # Onboarding screen 2
├── Intro3_Activity.kt           # Onboarding screen 3
└── Category Activities/
    ├── FoodActivity.kt
    ├── EmotionsActivity.kt
    ├── ActionsActivity.kt
    ├── PeopleActivity.kt
    ├── PlacesActivity.kt
    ├── ColorsActivity.kt
    ├── NumbersActivity.kt
    └── WeatherActivity.kt
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version)
- Android device or emulator with API 24+
- ElevenLabs account and API key

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/yourusername/hear-me-out.git
```

2. **Open in Android Studio:**
   - File → Open → select the project folder

3. **Add your ElevenLabs API key:**

   Open `ElevenLabsTTS.kt` and replace:
   ```kotlin
   private const val API_KEY = "YOUR_API_KEY_HERE"
   private const val MALE_VOICE_ID = "YOUR_MALE_VOICE_ID"
   private const val FEMALE_VOICE_ID = "YOUR_FEMALE_VOICE_ID"
   ```

4. **Sync the project:**
   - Click **Sync Now** when prompted

5. **Run the app:**
   - Connect your Android device via USB with USB Debugging enabled
   - Click the **Run ▶** button

---

## 🔑 API Configuration

### ElevenLabs TTS
- Sign up at [https://elevenlabs.io](https://elevenlabs.io)
- Get your API key from **Profile → API Keys**
- Select voices from the Voice Library that support Arabic
- The app uses `eleven_multilingual_v2` model for best Arabic/English quality

### Google ML Kit
- No API key required
- The Arabic handwriting recognition model is downloaded automatically on first use
- Requires internet connection for the initial model download only

---

## 📊 Evaluation

The application was evaluated using:
- **SUS (System Usability Scale)** score: **88.75 / 100** — Excellent rating
- **Task-based testing** with 12 participants
- **Performance and response time** measurement

---

## ⚠️ Known Limitations

- ElevenLabs API key is embedded in the app — suitable for demo and academic purposes only. A backend server should be used in production to secure the API key.
- Arabic handwriting recognition accuracy is moderate. Works best with clearly printed letters due to the cursive nature of Arabic script.
- TTS output for Egyptian dialect is based on Modern Standard Arabic phonetics. Pre-recorded audio is recommended for the most accurate dialect output in child mode.
- Internet connection is required for TTS and first-time ML Kit model download.

---

## 🔮 Future Work

- OCR Option
- Voice cloning feature using ElevenLabs to allow users to use their own voice
- Eye-gaze input support for users with severe motor impairments
- Expanded flashcard library with custom card creation for parents and therapists
- Offline TTS support using on-device models



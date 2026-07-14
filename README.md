# SmartStart Synergy

An interactive Early Childhood Development (ECD) Android app for children aged 3–6. Children learn letters, numbers, shapes, colours and animals through colourful flashcards, spoken prompts and mini-games, while progress is tracked on-device.

Built with **Kotlin**, **Jetpack Compose**, **Material 3** and **Room**.

## 🎯 Status — Phase 1 Complete + Phase 2 Enhancements

### ✅ Implemented Features

**Learning Modules:**
- **Alphabet (A–Z)** – Flashcards with Text-to-Speech pronunciation and example words
- **Numbers (0–20)** – Visual counting with emoji representations
- **Shapes** – 10 procedurally-drawn shapes with colors
- **Colors** – Interactive color learning with 11 vibrant palettes
- **Animals** – 14 animals (domestic & wild) with sound descriptions

**Games:**
- 🎈 **Balloon Pop** – Tap balloons to pop them for points
- 🎯 **Shape Match** – Match shapes to their outlines
- 🦁 **Animal Sounds Quiz** – Guess animals by their sounds
- 🧠 **Memory Game** – Match pairs of emojis (NEW)

**Portals & Dashboards:**
- 📊 **Progress Screen** – Stars earned, module completion, high scores
- 🏆 **Rewards & Badges** – Unlock achievements for milestones
- 👨‍🏫 **Teacher Mode** – Class overview, student stats, performance analytics
- 👨‍👩‍👧 **Parent Portal** – Child progress tracking, engagement tips
- 👨‍💻 **About Developer** – Project information & credits

**Data & Persistence:**
- ✅ **Room Database** – On-device progress storage (fully offline)
- ✅ **Progress Tracking** – Stars per module (0–3), game high scores
- ✅ **State Management** – ViewModel + StateFlow for reactive updates

### Roadmap

- **Phase 2** – More games (jigsaw, find-the-difference), multiplayer modes
- **Phase 3** – Child profiles, parent-teacher messaging, achievements
- **Phase 4** – Multi-language (English, Chichewa, Tumbuka), cloud sync, AI-assisted learning

## 📁 Project Structure

```
app/src/main/java/com/smartstart/synergy/
├── data/
│   ├── content/          # Static learning content + Catalog (modules, games)
│   ├── database/         # Room entities, DAO, AppDatabase
│   └── repository/       # ProgressRepository (data abstraction)
├── ui/
│   ├── components/       # Shared composables (BigTile, Flashcard, Speaker, ShapeView)
│   ├── navigation/       # Routes, NavGraph
│   ├── splash/           # Launch screen (3-second animation)
│   ├── welcome/          # Onboarding screen
│   ├── home/             # Dashboard with 7 tiles (Learn, Games, Progress, etc.)
│   ├── learn/            # Module screens (Alphabet, Numbers, Shapes, Colors, Animals)
│   ├── games/            # Game implementations (Balloon Pop, Shape Match, Animal Quiz, Memory)
│   ├── progress/         # Progress & stats display
│   ├── rewards/          # Badge/achievement showcase
│   ├── teacher/          # Teacher Mode dashboard
│   ├── parent/           # Parent Portal
│   ├── about/            # About screen
│   └── theme/            # Material 3 colors & typography
├── viewmodel/            # ProgressViewModel (state management)
├── MainActivity.kt       # Activity entry point
└── SmartStartApp.kt      # Application class (dependency setup)
```

### How It Fits Together

1. **App Initialization** → `SmartStartApp` creates Room database & `ProgressRepository` singleton
2. **Navigation Flow** → `MainActivity` instantiates `SmartStartNavGraph` with `ProgressViewModel`
3. **User Journey** → Splash → Welcome → Home → Learn/Games/Progress/etc.
4. **Progress Recording** → Learning & game screens call `progressViewModel.on*()` methods
5. **Data Persistence** → Room DAO writes to SQLite; StateFlow emits updates to UI
6. **Offline-First** → All content (letters, shapes, emojis) embedded in code; no external assets

## 🚀 Build & Run

### Requirements
- **JDK 17** or later
- **Android SDK 34** (compileSdk)
- **Min SDK 24** (Android 7.0)

### Quick Start

```bash
# Clone the repo
git clone https://github.com/noel200-a/NewSmartStart.git
cd NewSmartStart

# Build debug APK
./gradlew :app:assembleDebug

# Install on emulator/device
./gradlew :app:installDebug

# Run directly (one command)
./gradlew :app:installDebug -PdeviceId=<device_id>
```

**Output:** `app/build/outputs/apk/debug/app-debug.apk`

### IDE Setup (Android Studio)

1. Open project in Android Studio
2. Sync Gradle (`File → Sync Now`)
3. Run app: `Run → Run 'app'` or `Shift+F10`
4. Select emulator or connected device

## 📊 Database Schema

### ModuleProgressEntity
```kotlin
val moduleId: String      // Primary key (e.g., "alphabet")
val completedItems: Int   // Items seen (0..total)
val totalItems: Int       // Total items in module
val stars: Int            // Achievement level (0–3)
val updatedAt: Long       // Last update timestamp
```

### GamePlayEntity
```kotlin
val id: Long              // Auto-increment primary key
val gameId: String        // Game ID (e.g., "balloon_pop")
val score: Int            // Points earned
val playedAt: Long        // Play timestamp
```

## 🎨 Design System

**Color Palette (Material 3):**
- 🔴 **Coral** (`#FF7B54`) – Primary accent
- 🔵 **SkyBlue** (`#00A8E8`) – Secondary
- 🟢 **Grass** (`#06D6A0`) – Success/positive
- 🟡 **Amber** (`#FFD60A`) – Rewards/highlights
- 🟣 **Pink** (`#D60A56`) – Accent
- 🔷 **Teal** (`#118AB2`) – Analytics

**Typography:**
- Headlines: Roboto Bold (ExtraLarge 26sp)
- Body: Roboto Regular (Medium 16sp)
- Captions: Roboto Medium (Small 12sp)

## 🧪 Testing

### Manual Testing Checklist

- [ ] Splash screen plays 3-second animation
- [ ] Welcome screen transitions to Home on button tap
- [ ] All 5 learning modules load and display flashcards
- [ ] TTS pronunciation works (requires speaker/earphone)
- [ ] All 4 games play without crashes
- [ ] Progress persists after app restart
- [ ] Teacher/Parent portals display mock data
- [ ] Rewards screen shows badges (locked/unlocked)
- [ ] Back button navigates correctly
- [ ] Offline functionality works (no network required)

### Unit Tests

```bash
./gradlew :app:testDebug
```

### Instrumented Tests

```bash
./gradlew :app:connectedAndroidTest
```

## 📱 Device Compatibility

- **Min SDK:** Android 7.0 (API 24)
- **Target SDK:** Android 14 (API 34)
- **Screen Sizes:** Phone (320–480dp) & Tablet (600–1920dp) supported
- **Orientation:** Portrait lock (recommended for young children)

## 🔐 Permissions

```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.INTERNET" />  <!-- Optional for future updates -->
```

Currently, the app runs **100% offline** with no special permissions required.

## 🤝 Contributing

1. Fork the repo
2. Create a feature branch: `git checkout -b feature/memory-game-v2`
3. Commit changes: `git commit -m "feat: improve memory game difficulty levels"`
4. Push to branch: `git push origin feature/memory-game-v2`
5. Open a Pull Request

## 📝 License

This project is open-source. See `LICENSE` file for details.

## 👨‍💻 About

**SmartStart Synergy** was created to make early childhood learning engaging, interactive, and accessible to all children. Built with ❤️ by the SmartStart team.

### Credits

- **UI/UX:** Jetpack Compose + Material Design 3
- **Data:** Room + SQLite
- **Audio:** Android TTS (Text-to-Speech)
- **Graphics:** Procedurally-drawn shapes (no external assets)

---

**Latest Version:** 0.1.0  
**Last Updated:** July 2026  
**Status:** Phase 1 Complete ✅ | Phase 2 In Progress 🚀


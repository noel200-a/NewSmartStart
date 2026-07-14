# SmartStart Synergy

An interactive Early Childhood Development (ECD) Android app for children aged 3–6. Children learn
letters, numbers, shapes, colours and animals through colourful flashcards, spoken prompts and
mini-games, while progress is tracked on-device.

Built with **Kotlin**, **Jetpack Compose**, **Material 3** and **Room**.

## Status — Phase 1 (Core Learning)

Implemented in this repository:

- Splash → Welcome → Home dashboard navigation
- **Learning modules:** Alphabet (A–Z), Numbers (0–20), Shapes, Colors, Animals — flashcards with
  Text-to-Speech pronunciation and a progress bar
- **Games:** Balloon Pop, Shape Match, Animal Sounds quiz — each awards a score and celebrates
- **Progress tracking:** stars per module, module completion and game high scores, persisted with Room
- Fully **offline** — content lives in code and shapes are drawn procedurally, so no bundled assets

Dashboard tiles for Rewards, Teacher Mode and Parent Mode are present but marked *coming soon*.

### Roadmap

- **Phase 2** – more games (memory, jigsaw, find-the-difference)
- **Phase 3** – child profiles, parent & teacher dashboards, achievements/rewards
- **Phase 4** – multi-language (English, Chichewa, Tumbuka), cloud sync, AI-assisted learning

## Project structure

```
app/src/main/java/com/smartstart/synergy/
├── data/
│   ├── content/     # static learning content + module/game catalog
│   ├── database/    # Room entities, DAO, database
│   └── repository/  # ProgressRepository
├── ui/
│   ├── components/  # shared composables (Speaker/TTS, ShapeView, tiles)
│   ├── navigation/  # routes + NavGraph
│   ├── splash/ welcome/ home/ learn/ games/ progress/
│   └── theme/
├── viewmodel/       # ProgressViewModel
├── MainActivity.kt
└── SmartStartApp.kt
```

## Build & run

Requires JDK 17 and the Android SDK (compileSdk 34).

```bash
./gradlew :app:assembleDebug          # build debug APK
./gradlew installDebug                # install on a connected device/emulator
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`.

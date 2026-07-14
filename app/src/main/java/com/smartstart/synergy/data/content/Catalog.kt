package com.smartstart.synergy.data.content

object GameIds {
    const val BALLOON_POP = "balloon_pop"
    const val SHAPE_MATCH = "shape_match"
    const val ANIMAL_QUIZ = "animal_quiz"
    const val MEMORY = "memory"
}

object ModuleIds {
    const val ALPHABET = "alphabet"
    const val NUMBERS = "numbers"
    const val SHAPES = "shapes"
    const val COLORS = "colors"
    const val ANIMALS = "animals"
}

data class ModuleInfo(val id: String, val title: String, val emoji: String, val itemCount: Int)

data class GameInfo(val id: String, val title: String, val emoji: String)

object Catalog {
    val modules = listOf(
        ModuleInfo(ModuleIds.ALPHABET, "Alphabet", "🔤", 26),
        ModuleInfo(ModuleIds.NUMBERS, "Numbers", "🔢", 21),
        ModuleInfo(ModuleIds.SHAPES, "Shapes", "🔷", 10),
        ModuleInfo(ModuleIds.COLORS, "Colors", "🎨", 11),
        ModuleInfo(ModuleIds.ANIMALS, "Animals", "🦁", 14),
    )

    val games = listOf(
        GameInfo(GameIds.BALLOON_POP, "Balloon Pop", "🎈"),
        GameInfo(GameIds.SHAPE_MATCH, "Shape Match", "🎯"),
        GameInfo(GameIds.ANIMAL_QUIZ, "Animal Sounds", "🦁"),
        GameInfo(GameIds.MEMORY, "Memory", "🧠"),
    )
}

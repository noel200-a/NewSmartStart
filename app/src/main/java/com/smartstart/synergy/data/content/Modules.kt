package com.smartstart.synergy.data.content

import androidx.compose.ui.graphics.Color
import com.smartstart.synergy.ui.theme.Amber
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.ui.theme.Teal

/** Stable identifiers used as keys for progress tracking. */
object ModuleIds {
    const val ALPHABET = "alphabet"
    const val NUMBERS = "numbers"
    const val SHAPES = "shapes"
    const val COLORS = "colors"
    const val ANIMALS = "animals"
}

object GameIds {
    const val BALLOON_POP = "balloon_pop"
    const val SHAPE_MATCH = "shape_match"
    const val ANIMAL_QUIZ = "animal_quiz"
}

data class LearningModule(
    val id: String,
    val title: String,
    val emoji: String,
    val color: Color,
    val itemCount: Int,
)

data class GameInfo(
    val id: String,
    val title: String,
    val emoji: String,
    val color: Color,
)

object Catalog {
    val modules: List<LearningModule> = listOf(
        LearningModule(ModuleIds.ALPHABET, "Alphabet", "🔤", Coral, Content.alphabet.size),
        LearningModule(ModuleIds.NUMBERS, "Numbers", "🔢", SkyBlue, Content.numbers.size),
        LearningModule(ModuleIds.SHAPES, "Shapes", "🔺", Grass, Content.shapes.size),
        LearningModule(ModuleIds.COLORS, "Colors", "🎨", Amber, Content.colors.size),
        LearningModule(ModuleIds.ANIMALS, "Animals", "🐾", Teal, Content.animals.size),
    )

    val games: List<GameInfo> = listOf(
        GameInfo(GameIds.BALLOON_POP, "Balloon Pop", "🎈", Coral),
        GameInfo(GameIds.SHAPE_MATCH, "Shape Match", "🔷", SkyBlue),
        GameInfo(GameIds.ANIMAL_QUIZ, "Animal Sounds", "🔊", Pink),
    )

    fun moduleById(id: String): LearningModule? = modules.firstOrNull { it.id == id }
}

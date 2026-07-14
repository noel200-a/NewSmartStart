package com.smartstart.synergy.ui.navigation

object Routes {
    const val SPLASH = "splash"
    const val WELCOME = "welcome"
    const val HOME = "home"
    const val LEARN = "learn"
    const val GAMES = "games"
    const val PROGRESS = "progress"
    const val ABOUT = "about"
    const val REWARDS = "rewards"
    const val TEACHER_MODE = "teacher_mode"
    const val PARENT_MODE = "parent_mode"

    const val MODULE = "module/{moduleId}"
    fun module(moduleId: String) = "module/$moduleId"

    const val GAME_BALLOON = "game/balloon"
    const val GAME_SHAPE_MATCH = "game/shape_match"
    const val GAME_ANIMAL_QUIZ = "game/animal_quiz"
    const val GAME_MEMORY = "game/memory"
}

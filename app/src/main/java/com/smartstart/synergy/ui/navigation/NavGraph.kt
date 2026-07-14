package com.smartstart.synergy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smartstart.synergy.data.content.GameIds
import com.smartstart.synergy.data.content.ModuleIds
import com.smartstart.synergy.ui.games.AnimalQuizGame
import com.smartstart.synergy.ui.games.BalloonPopGame
import com.smartstart.synergy.ui.games.GamesMenuScreen
import com.smartstart.synergy.ui.games.ShapeMatchGame
import com.smartstart.synergy.ui.games.MemoryGame
import com.smartstart.synergy.ui.home.HomeScreen
import com.smartstart.synergy.ui.about.AboutScreen
import com.smartstart.synergy.ui.learn.AlphabetScreen
import com.smartstart.synergy.ui.learn.AnimalsScreen
import com.smartstart.synergy.ui.learn.ColorsScreen
import com.smartstart.synergy.ui.learn.LearnMenuScreen
import com.smartstart.synergy.ui.learn.NumbersScreen
import com.smartstart.synergy.ui.learn.ShapesScreen
import com.smartstart.synergy.ui.progress.ProgressScreen
import com.smartstart.synergy.ui.splash.SplashScreen
import com.smartstart.synergy.ui.welcome.WelcomeScreen
import com.smartstart.synergy.ui.rewards.RewardsScreen
import com.smartstart.synergy.ui.teacher.TeacherModeScreen
import com.smartstart.synergy.ui.parent.ParentModeScreen
import com.smartstart.synergy.viewmodel.ProgressViewModel

@Composable
fun SmartStartNavGraph(
    progressViewModel: ProgressViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(onFinished = {
                navController.navigate(Routes.WELCOME) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }
        composable(Routes.WELCOME) {
            WelcomeScreen(onStart = {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.WELCOME) { inclusive = true }
                }
            })
        }
        composable(Routes.HOME) {
            HomeScreen(
                onLearn = { navController.navigate(Routes.LEARN) },
                onGames = { navController.navigate(Routes.GAMES) },
                onProgress = { navController.navigate(Routes.PROGRESS) },
                onAbout = { navController.navigate(Routes.ABOUT) },
                onRewards = { navController.navigate(Routes.REWARDS) },
                onTeacherMode = { navController.navigate(Routes.TEACHER_MODE) },
                onParentMode = { navController.navigate(Routes.PARENT_MODE) },
            )
        }
        composable(Routes.LEARN) {
            LearnMenuScreen(
                onBack = { navController.popBackStack() },
                onModule = { id -> navController.navigate(Routes.module(id)) },
            )
        }
        composable(Routes.MODULE) { entry ->
            val moduleId = entry.arguments?.getString("moduleId").orEmpty()
            val back = { navController.popBackStack(); Unit }
            when (moduleId) {
                ModuleIds.ALPHABET -> AlphabetScreen(progressViewModel, back)
                ModuleIds.NUMBERS -> NumbersScreen(progressViewModel, back)
                ModuleIds.SHAPES -> ShapesScreen(progressViewModel, back)
                ModuleIds.COLORS -> ColorsScreen(progressViewModel, back)
                ModuleIds.ANIMALS -> AnimalsScreen(progressViewModel, back)
                else -> back()
            }
        }
        composable(Routes.GAMES) {
            GamesMenuScreen(
                onBack = { navController.popBackStack() },
                onGame = { id ->
                    when (id) {
                        GameIds.BALLOON_POP -> navController.navigate(Routes.GAME_BALLOON)
                        GameIds.SHAPE_MATCH -> navController.navigate(Routes.GAME_SHAPE_MATCH)
                        GameIds.ANIMAL_QUIZ -> navController.navigate(Routes.GAME_ANIMAL_QUIZ)
                        GameIds.MEMORY -> navController.navigate(Routes.GAME_MEMORY)
                    }
                },
            )
        }
        composable(Routes.GAME_BALLOON) {
            BalloonPopGame(progressViewModel) { navController.popBackStack() }
        }
        composable(Routes.GAME_SHAPE_MATCH) {
            ShapeMatchGame(progressViewModel) { navController.popBackStack() }
        }
        composable(Routes.GAME_ANIMAL_QUIZ) {
            AnimalQuizGame(progressViewModel) { navController.popBackStack() }
        }
        composable(Routes.GAME_MEMORY) {
            MemoryGame(progressViewModel) { navController.popBackStack() }
        }
        composable(Routes.PROGRESS) {
            ProgressScreen(progressViewModel) { navController.popBackStack() }
        }
        composable(Routes.ABOUT) {
            AboutScreen { navController.popBackStack() }
        }
        composable(Routes.REWARDS) {
            RewardsScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.TEACHER_MODE) {
            TeacherModeScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.PARENT_MODE) {
            ParentModeScreen(onBack = { navController.popBackStack() })
        }
    }
}

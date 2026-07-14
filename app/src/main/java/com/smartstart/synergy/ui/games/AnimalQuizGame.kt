package com.smartstart.synergy.ui.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.data.content.Animal
import com.smartstart.synergy.data.content.Content
import com.smartstart.synergy.data.content.GameIds
import com.smartstart.synergy.ui.components.rememberSpeaker
import com.smartstart.synergy.viewmodel.ProgressViewModel

private const val ROUNDS = 8
private const val OPTIONS = 4

@Composable
fun AnimalQuizGame(viewModel: ProgressViewModel, onBack: () -> Unit) {
    val speaker = rememberSpeaker()
    var round by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }
    var options by remember { mutableStateOf(newOptions()) }
    var target by remember { mutableStateOf(options.random()) }
    var wrong by remember { mutableStateOf<Animal?>(null) }

    LaunchedEffect(target) { speaker.speak(target.sound) }

    fun nextRound() {
        wrong = null
        if (round + 1 >= ROUNDS) {
            finished = true
            viewModel.onGameFinished(GameIds.ANIMAL_QUIZ, score + 1)
        } else {
            round++
            options = newOptions()
            target = options.random()
        }
    }

    GameShell(
        title = "Animal Sounds",
        prompt = "Which animal says this?",
        score = score,
        round = round + 1,
        totalRounds = ROUNDS,
        onBack = onBack,
    ) { _ ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Button(
                onClick = { speaker.speak(target.sound) },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                ),
            ) {
                Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null)
                Text("  Hear the sound", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(options) { animal ->
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(if (wrong == animal) Color(0xFFEEEEEE) else Color.White)
                            .border(2.dp, Color(0xFFE0E0E0), RoundedCornerShape(24.dp))
                            .clickable {
                                if (animal.name == target.name) {
                                    score++
                                    nextRound()
                                } else {
                                    wrong = animal
                                }
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(animal.emoji, fontSize = 72.sp)
                    }
                }
            }
        }
    }

    if (finished) {
        CelebrationDialog(
            score = score,
            total = ROUNDS,
            onReplay = {
                round = 0; score = 0; finished = false
                options = newOptions(); target = options.random(); wrong = null
            },
            onExit = onBack,
        )
    }
}

private fun newOptions(): List<Animal> = Content.animals.shuffled().take(OPTIONS)

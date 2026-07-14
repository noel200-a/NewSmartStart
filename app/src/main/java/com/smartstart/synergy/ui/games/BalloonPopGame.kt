package com.smartstart.synergy.ui.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.smartstart.synergy.data.content.GameIds
import com.smartstart.synergy.ui.theme.Amber
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.ui.theme.Teal
import com.smartstart.synergy.viewmodel.ProgressViewModel

private const val ROUNDS = 8
private const val OPTIONS = 6
private val balloonColors = listOf(Coral, SkyBlue, Grass, Amber, Pink, Teal)

@Composable
fun BalloonPopGame(viewModel: ProgressViewModel, onBack: () -> Unit) {
    var round by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }
    var options by remember { mutableStateOf(newOptions()) }
    var target by remember { mutableStateOf(options.random()) }
    var wrong by remember { mutableStateOf<Char?>(null) }

    fun nextRound() {
        wrong = null
        if (round + 1 >= ROUNDS) {
            finished = true
            viewModel.onGameFinished(GameIds.BALLOON_POP, score + 1)
        } else {
            round++
            options = newOptions()
            target = options.random()
        }
    }

    GameShell(
        title = "Balloon Pop",
        prompt = "Pop the letter \"$target\"",
        score = score,
        round = round + 1,
        totalRounds = ROUNDS,
        onBack = onBack,
    ) { _ ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(options) { letter ->
                Balloon(
                    letter = letter,
                    color = balloonColors[options.indexOf(letter) % balloonColors.size],
                    isWrong = wrong == letter,
                ) {
                    if (letter == target) {
                        score++
                        nextRound()
                    } else {
                        wrong = letter
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

@Composable
private fun Balloon(letter: Char, color: Color, isWrong: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(48.dp))
            .background(if (isWrong) Color.Gray else color)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(letter.toString(), color = Color.White, fontSize = 44.sp, fontWeight = FontWeight.ExtraBold)
    }
}

private fun newOptions(): List<Char> =
    ('A'..'Z').shuffled().take(OPTIONS)

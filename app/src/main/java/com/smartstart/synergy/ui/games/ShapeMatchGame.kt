package com.smartstart.synergy.ui.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import com.smartstart.synergy.data.content.Content
import com.smartstart.synergy.data.content.GameIds
import com.smartstart.synergy.data.content.ShapeItem
import com.smartstart.synergy.ui.components.ShapeView
import com.smartstart.synergy.viewmodel.ProgressViewModel

private const val ROUNDS = 8
private const val OPTIONS = 4

@Composable
fun ShapeMatchGame(viewModel: ProgressViewModel, onBack: () -> Unit) {
    var round by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }
    var options by remember { mutableStateOf(newOptions()) }
    var target by remember { mutableStateOf(options.random()) }
    var wrong by remember { mutableStateOf<ShapeItem?>(null) }

    fun nextRound() {
        wrong = null
        if (round + 1 >= ROUNDS) {
            finished = true
            viewModel.onGameFinished(GameIds.SHAPE_MATCH, score + 1)
        } else {
            round++
            options = newOptions()
            target = options.random()
        }
    }

    GameShell(
        title = "Shape Match",
        prompt = "Find the ${target.label}",
        score = score,
        round = round + 1,
        totalRounds = ROUNDS,
        onBack = onBack,
    ) { _ ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(options) { shape ->
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (wrong == shape) Color(0xFFEEEEEE) else Color.White)
                        .border(2.dp, Color(0xFFE0E0E0), RoundedCornerShape(24.dp))
                        .clickable {
                            if (shape.kind == target.kind) {
                                score++
                                nextRound()
                            } else {
                                wrong = shape
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    ShapeView(kind = shape.kind, color = shape.color, modifier = Modifier.size(100.dp))
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

private fun newOptions(): List<ShapeItem> = Content.shapes.shuffled().take(OPTIONS)

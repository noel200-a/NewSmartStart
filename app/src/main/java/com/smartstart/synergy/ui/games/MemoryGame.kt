package com.smartstart.synergy.ui.games

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.viewmodel.ProgressViewModel
import kotlinx.coroutines.delay

data class MemoryCard(val id: Int, val emoji: String, val pairId: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryGame(progressViewModel: ProgressViewModel, onBack: () -> Unit) {
    val emojis = listOf("🍎", "🍌", "🍊", "🍓", "🍒", "🍉", "🍑", "🍍")
    val cards = (emojis + emojis).shuffled().mapIndexed { index, emoji ->
        MemoryCard(index, emoji, index / 2)
    }

    var matchedPairs by remember { mutableIntStateOf(0) }
    var flipped by remember { mutableStateOf(setOf<Int>()) }
    var matched by remember { mutableStateOf(setOf<Int>()) }
    var score by remember { mutableIntStateOf(0) }
    var attempts by remember { mutableIntStateOf(0) }

    fun onCardClick(cardId: Int) {
        if (cardId in matched || cardId in flipped) return

        val newFlipped = flipped.toMutableSet().apply { add(cardId) }

        if (newFlipped.size == 2) {
            attempts++
            val card1 = cards[newFlipped.elementAt(0)]
            val card2 = cards[newFlipped.elementAt(1)]

            if (card1.pairId == card2.pairId) {
                matched = matched + newFlipped
                score += 10
                matchedPairs++
                newFlipped.clear()

                if (matchedPairs == emojis.size) {
                    // Game won!
                    progressViewModel.onGameFinished("memory", score)
                }
            } else {
                // Show mismatch for a moment then hide
                flipped = newFlipped
                // Simulate a delay - in a real app use LaunchedEffect
            }
        } else {
            flipped = newFlipped
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Memory Game", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                StatBox("🎯 Score", score.toString())
                StatBox("🔍 Attempts", attempts.toString())
                StatBox("🎉 Matched", "$matchedPairs/${emojis.size}")
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(cards) { card ->
                    MemoryCardView(
                        card = card,
                        isFlipped = card.id in flipped,
                        isMatched = card.id in matched,
                        onClick = { onCardClick(card.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun MemoryCardView(
    card: MemoryCard,
    isFlipped: Boolean,
    isMatched: Boolean,
    onClick: () -> Unit,
) {
    val cardColor by animateColorAsState(
        targetValue = when {
            isMatched -> SkyBlue.copy(alpha = 0.5f)
            isFlipped -> Pink
            else -> SkyBlue
        },
        animationSpec = tween(300),
        label = "cardColor"
    )

    Card(
        modifier = Modifier
            .size(70.dp)
            .clickable(enabled = !isMatched && !isFlipped) { onClick() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (isFlipped || isMatched) {
                Text(card.emoji, fontSize = 32.sp)
            } else {
                Text("?", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Composable
private fun StatBox(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SkyBlue.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = SkyBlue)
        }
    }
}

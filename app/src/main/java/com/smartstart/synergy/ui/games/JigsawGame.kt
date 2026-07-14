package com.smartstart.synergy.ui.games

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.LinearProgressIndicator
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
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.viewmodel.ProgressViewModel

data class PuzzlePiece(val id: Int, val emoji: String, val position: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JigsawGame(progressViewModel: ProgressViewModel, onBack: () -> Unit) {
    val puzzleEmojis = listOf(
        "🌞" to "Sun",
        "🌙" to "Moon",
        "⭐" to "Star",
        "🌈" to "Rainbow",
        "🌻" to "Flower",
        "🐝" to "Bee",
    )

    var correctCount by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var currentPuzzleIndex by remember { mutableIntStateOf(0) }
    var selectedPiece by remember { mutableStateOf<Int?>(null) }
    var placedPieces by remember { mutableStateOf(setOf<Int>()) }
    var showWinAnimation by remember { mutableStateOf(false) }

    val currentPuzzle = puzzleEmojis[currentPuzzleIndex]
    val pieces = (0..3).map { it }.shuffled()

    fun checkPlacement(piece: Int, slot: Int) {
        if (piece == slot) {
            placedPieces = placedPieces + piece
            score += 15
            if (placedPieces.size == 4) {
                correctCount++
                if (correctCount >= puzzleEmojis.size) {
                    progressViewModel.onGameFinished("jigsaw", score)
                }
                // Move to next puzzle
                currentPuzzleIndex++
                placedPieces = setOf()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jigsaw Game", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
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
        if (currentPuzzleIndex >= puzzleEmojis.size) {
            // Game Complete
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        "🎉",
                        fontSize = 80.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                    Text(
                        "Puzzle Complete!",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Grass,
                    )
                    Text(
                        "Final Score: $score",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Coral,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                    Text(
                        "Puzzles Completed: $correctCount/${puzzleEmojis.size}",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
        } else {
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
                    StatBox("📦 Puzzles", "$correctCount/${puzzleEmojis.size}")
                    StatBox("🎁 Placed", "${placedPieces.size}/4")
                }

                // Puzzle Image
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = SkyBlue.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            currentPuzzle.first,
                            fontSize = 80.sp,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )
                        Text(
                            "Find: ${currentPuzzle.second}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }

                Text(
                    "Drag pieces to correct slots below:",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 12.dp),
                )

                // Drop zones (slots)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                ) {
                    items(4) { slot ->
                        DropZone(
                            slot = slot,
                            isOccupied = slot in placedPieces,
                            onDrop = { checkPlacement(it, slot) },
                        )
                    }
                }

                // Puzzle Pieces
                Text(
                    "Available Pieces:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    pieces.forEach { piece ->
                        if (piece !in placedPieces) {
                            PuzzlePieceButton(
                                piece = piece,
                                isSelected = selectedPiece == piece,
                                onClick = { selectedPiece = if (selectedPiece == piece) null else piece },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DropZone(
    slot: Int,
    isOccupied: Boolean,
    onDrop: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isOccupied) Grass.copy(alpha = 0.3f) else Pink.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(
                    color = if (isOccupied) Grass.copy(alpha = 0.2f) else Pink.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (isOccupied) {
                Text("✓", fontSize = 32.sp, color = Grass, fontWeight = FontWeight.Bold)
            } else {
                Text("?", fontSize = 24.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
private fun PuzzlePieceButton(
    piece: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(60.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Coral else SkyBlue
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "${piece + 1}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
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
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = SkyBlue)
        }
    }
}

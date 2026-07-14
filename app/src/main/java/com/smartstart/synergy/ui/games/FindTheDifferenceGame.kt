package com.smartstart.synergy.ui.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.smartstart.synergy.ui.theme.Amber
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.viewmodel.ProgressViewModel

data class DifferenceItem(val emoji: String, val label: String, val difference: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindTheDifferenceGame(progressViewModel: ProgressViewModel, onBack: () -> Unit) {
    val puzzles = listOf(
        DifferenceItem("🍎", "Apple", "Color change: Red → Green"),
        DifferenceItem("🐶", "Dog", "Spot added on head"),
        DifferenceItem("🌳", "Tree", "Extra branch on left"),
        DifferenceItem("🏠", "House", "Window missing on right"),
        DifferenceItem("⛅", "Cloud", "Cloud shape changed"),
    )

    var currentPuzzleIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var found by remember { mutableStateOf(false) }
    var hintUsed by remember { mutableStateOf(false) }
    var showHint by remember { mutableStateOf(false) }

    val currentPuzzle = puzzles[currentPuzzleIndex]

    fun onFoundDifference() {
        found = true
        score += if (hintUsed) 5 else 10
    }

    fun onNextPuzzle() {
        if (currentPuzzleIndex < puzzles.size - 1) {
            currentPuzzleIndex++
            found = false
            hintUsed = false
            showHint = false
        } else {
            // Game complete
            progressViewModel.onGameFinished("find_difference", score)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Find the Difference", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
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
                StatBox("🔍 Level", "${currentPuzzleIndex + 1}/${puzzles.size}")
            }

            // Image comparison area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // Left image (original)
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = SkyBlue.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Original", fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
                        Text(currentPuzzle.emoji, fontSize = 60.sp, modifier = Modifier.padding(8.dp))
                    }
                }

                // Right image (with difference)
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .clickable { if (!found) onFoundDifference() },
                    colors = CardDefaults.cardColors(
                        containerColor = if (found) Grass.copy(alpha = 0.2f) else Coral.copy(alpha = 0.2f)
                    ),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Different", fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
                        Text(currentPuzzle.emoji, fontSize = 60.sp, modifier = Modifier.padding(8.dp))
                        if (found) {
                            Text("✓", fontSize = 28.sp, color = Grass)
                        } else {
                            Text("?", fontSize = 28.sp, color = Coral)
                        }
                    }
                }
            }

            // Hint button
            if (!found && !hintUsed) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            hintUsed = true
                            showHint = true
                        }
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Amber.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text("💡 Need a hint?", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            // Hint display
            if (showHint) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Pink.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                    ) {
                        Text("Hint:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Pink)
                        Text(currentPuzzle.difference, fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground)
                    }
                }
            }

            // Next button
            if (found) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNextPuzzle() },
                    colors = CardDefaults.cardColors(containerColor = Grass),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "Next Puzzle →",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
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

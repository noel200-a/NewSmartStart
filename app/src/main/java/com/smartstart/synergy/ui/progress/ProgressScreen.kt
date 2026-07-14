package com.smartstart.synergy.ui.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.data.content.Catalog
import com.smartstart.synergy.data.content.GameInfo
import com.smartstart.synergy.ui.components.SectionScaffold
import com.smartstart.synergy.viewmodel.ProgressViewModel

@Composable
fun ProgressScreen(viewModel: ProgressViewModel, onBack: () -> Unit) {
    val moduleProgress by viewModel.moduleProgress.collectAsState()
    val gamePlays by viewModel.gamePlays.collectAsState()

    val totalStars = moduleProgress.sumOf { it.stars }
    val bestScores: Map<String, Int> = gamePlays.groupBy { it.gameId }
        .mapValues { entry -> entry.value.maxOf { it.score } }

    SectionScaffold(title = "My Progress", onBack = onBack) { modifier ->
        LazyColumn(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Card(shape = RoundedCornerShape(20.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("⭐ $totalStars", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
                        Text("Stars earned so far", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                    }
                }
            }

            item { SectionTitle("Learning modules") }
            items(Catalog.modules.size) { i ->
                val module = Catalog.modules[i]
                val progress = moduleProgress.firstOrNull { it.moduleId == module.id }
                val done = progress?.completedItems ?: 0
                val total = module.itemCount
                val stars = progress?.stars ?: 0
                Card(shape = RoundedCornerShape(20.dp)) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text("${module.emoji}  ${module.title}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("★".repeat(stars) + "☆".repeat(3 - stars))
                        }
                        LinearProgressIndicator(
                            progress = { if (total == 0) 0f else done.toFloat() / total },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .padding(top = 8.dp)
                                .clip(RoundedCornerShape(6.dp)),
                        )
                        Text(
                            "$done / $total explored",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 6.dp),
                        )
                    }
                }
            }

            item { SectionTitle("Game high scores") }
            items(Catalog.games.size) { i ->
                val game: GameInfo = Catalog.games[i]
                val best = bestScores[game.id]
                Card(shape = RoundedCornerShape(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("${game.emoji}  ${game.title}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(if (best != null) "🏆 $best" else "Not played yet")
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 8.dp),
        color = MaterialTheme.colorScheme.onBackground,
    )
}

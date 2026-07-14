package com.smartstart.synergy.ui.parent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.smartstart.synergy.ui.theme.Teal

data class ChildProgress(val module: String, val stars: Int, val completed: Int, val total: Int, val emoji: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentModeScreen(onBack: () -> Unit) {
    val childProgress = listOf(
        ChildProgress("Alphabet", 3, 26, 26, "🔤"),
        ChildProgress("Numbers", 2, 15, 20, "🔢"),
        ChildProgress("Shapes", 2, 4, 5, "🔷"),
        ChildProgress("Colors", 1, 4, 8, "🎨"),
        ChildProgress("Animals", 2, 6, 10, "🦁"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parent Portal", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
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
        ) {
            Text(
                "👋 Welcome, Parent!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Text(
                "Track your child's learning progress",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 24.dp),
            )

            Text(
                "📚 Module Progress",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 12.dp),
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(childProgress) { progress ->
                    ProgressCard(progress)
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = Pink.copy(alpha = 0.1f)),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "💡 Tips for Parents:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Pink,
                                modifier = Modifier.padding(bottom = 8.dp),
                            )
                            Text(
                                "• Encourage daily learning (5-10 mins)\n" +
                                    "• Celebrate milestones and stars\n" +
                                    "• Help with pronunciations\n" +
                                    "• Make learning fun!",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = SkyBlue.copy(alpha = 0.1f)),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "🔮 Coming Soon:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = SkyBlue,
                                modifier = Modifier.padding(bottom = 8.dp),
                            )
                            Text(
                                "• Weekly progress summaries\n" +
                                    "• Growth recommendations\n" +
                                    "• Activity history\n" +
                                    "• Parent-teacher messaging",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressCard(progress: ChildProgress) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(progress.emoji, fontSize = 28.sp)
                    Column {
                        Text(
                            progress.module,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            "⭐ ${progress.stars} stars",
                            fontSize = 12.sp,
                            color = Grass,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
                Text(
                    "${progress.completed}/${progress.total}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Teal,
                )
            }
            LinearProgressIndicator(
                progress = { progress.completed.toFloat() / progress.total.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                color = Teal,
                trackColor = Teal.copy(alpha = 0.2f),
            )
        }
    }
}

package com.smartstart.synergy.ui.rewards

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
import com.smartstart.synergy.ui.theme.Amber
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue

data class RewardBadge(val emoji: String, val title: String, val description: String, val color: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(onBack: () -> Unit) {
    val badges = listOf(
        RewardBadge("🌟", "First Step", "Complete your first learning module", SkyBlue),
        RewardBadge("⭐", "Star Collector", "Earn 10 stars across all modules", Amber),
        RewardBadge("🏆", "Champion", "Complete all 5 learning modules", Coral),
        RewardBadge("🎮", "Game Master", "Play all games", Grass),
        RewardBadge("🔥", "On Fire!", "Play 5 games in one day", Pink),
        RewardBadge("🎯", "Perfect Score", "Score 100 points in a game", SkyBlue),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rewards & Badges", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
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
                "Unlock badges and rewards by completing challenges!",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(badges) { badge ->
                    BadgeCard(badge)
                }
            }
        }
    }
}

@Composable
private fun BadgeCard(badge: RewardBadge) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(badge.color, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = badge.color),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                badge.emoji,
                fontSize = 40.sp,
                modifier = Modifier.padding(end = 8.dp),
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    badge.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Text(
                    badge.description,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.85f),
                )
            }
        }
    }
}

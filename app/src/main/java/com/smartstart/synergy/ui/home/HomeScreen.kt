package com.smartstart.synergy.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.ui.components.BigTile
import com.smartstart.synergy.ui.theme.Amber
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.ui.theme.Teal
import kotlinx.coroutines.launch

private data class DashItem(val emoji: String, val label: String, val color: Color, val action: HomeAction)

private enum class HomeAction { LEARN, GAMES, PROGRESS, ABOUT, SOON }

@Composable
fun HomeScreen(
    onLearn: () -> Unit,
    onGames: () -> Unit,
    onProgress: () -> Unit,
    onAbout: () -> Unit,
) {
    val snackbarHostState = rememberSnackbar()
    val scope = rememberCoroutineScope()

    val items = listOf(
        DashItem("📖", "Learn", Coral, HomeAction.LEARN),
        DashItem("🎮", "Games", SkyBlue, HomeAction.GAMES),
        DashItem("📊", "My Progress", Grass, HomeAction.PROGRESS),
        DashItem("👨‍💻", "About Developer", Teal, HomeAction.ABOUT),
        DashItem("🏆", "Rewards", Amber, HomeAction.SOON),
        DashItem("👨‍🏫", "Teacher Mode", Teal, HomeAction.SOON),
        DashItem("👨‍👩‍👧", "Parent Mode", Pink, HomeAction.SOON),
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Hello! 👋", fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
                Text(
                    "What shall we learn today?",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(items) { item ->
                    BigTile(
                        emoji = item.emoji,
                        label = item.label,
                        color = item.color,
                        subtitle = if (item.action == HomeAction.SOON) "Coming soon" else null,
                    ) {
                        when (item.action) {
                            HomeAction.LEARN -> onLearn()
                            HomeAction.GAMES -> onGames()
                            HomeAction.PROGRESS -> onProgress()
                            HomeAction.ABOUT -> onAbout()
                            HomeAction.SOON -> scope.launch {
                                snackbarHostState.showSnackbar("${item.label} is coming in a future update!")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberSnackbar(): SnackbarHostState {
    return androidx.compose.runtime.remember { SnackbarHostState() }
}

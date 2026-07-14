package com.smartstart.synergy.ui.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.smartstart.synergy.data.content.Catalog
import com.smartstart.synergy.ui.components.BigTile
import com.smartstart.synergy.ui.components.SectionScaffold

@Composable
fun GamesMenuScreen(onBack: () -> Unit, onGame: (String) -> Unit) {
    SectionScaffold(title = "Games", onBack = onBack) { modifier ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(Catalog.games) { game ->
                BigTile(emoji = game.emoji, label = game.title, color = game.color) {
                    onGame(game.id)
                }
            }
        }
    }
}

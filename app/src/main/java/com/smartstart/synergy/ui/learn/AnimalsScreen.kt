package com.smartstart.synergy.ui.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.data.content.Content
import com.smartstart.synergy.data.content.ModuleIds
import com.smartstart.synergy.ui.components.rememberSpeaker
import com.smartstart.synergy.viewmodel.ProgressViewModel

@Composable
fun AnimalsScreen(viewModel: ProgressViewModel, onBack: () -> Unit) {
    val speaker = rememberSpeaker()
    val items = Content.animals
    FlashcardScreen(
        title = "Animals",
        count = items.size,
        onBack = onBack,
        onIndexChanged = { viewModel.onModuleItemViewed(ModuleIds.ANIMALS, it, items.size) },
        onSpeak = { speaker.speak("${items[it].name}. ${items[it].sound}") },
        cardColor = { Color.White },
    ) { i ->
        val animal = items[i]
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(animal.emoji, fontSize = 130.sp)
            Text(animal.name, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
            Text(
                "\"${animal.sound}\"",
                fontSize = 22.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(top = 8.dp),
            )
            Text(
                if (animal.wild) "🌍 Wild animal" else "🏠 Farm animal",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

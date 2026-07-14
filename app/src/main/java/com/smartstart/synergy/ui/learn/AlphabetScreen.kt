package com.smartstart.synergy.ui.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.smartstart.synergy.data.content.Content
import com.smartstart.synergy.data.content.ModuleIds
import com.smartstart.synergy.ui.components.rememberSpeaker
import com.smartstart.synergy.viewmodel.ProgressViewModel

@Composable
fun AlphabetScreen(viewModel: ProgressViewModel, onBack: () -> Unit) {
    val speaker = rememberSpeaker()
    val items = Content.alphabet
    FlashcardScreen(
        title = "Alphabet",
        count = items.size,
        onBack = onBack,
        onIndexChanged = { viewModel.onModuleItemViewed(ModuleIds.ALPHABET, it, items.size) },
        onSpeak = { speaker.speak("${items[it].upper}. ${items[it].word}") },
        cardColor = { Color.White },
    ) { i ->
        val letter = items[i]
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("${letter.upper}${letter.lower}", fontSize = 120.sp, fontWeight = FontWeight.ExtraBold)
            Text(letter.emoji, fontSize = 72.sp)
            Text("is for ${letter.word}", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }
    }
}

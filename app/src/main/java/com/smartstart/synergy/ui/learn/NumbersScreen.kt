package com.smartstart.synergy.ui.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NumbersScreen(viewModel: ProgressViewModel, onBack: () -> Unit) {
    val speaker = rememberSpeaker()
    val items = Content.numbers
    FlashcardScreen(
        title = "Numbers",
        count = items.size,
        onBack = onBack,
        onIndexChanged = { viewModel.onModuleItemViewed(ModuleIds.NUMBERS, it, items.size) },
        onSpeak = { speaker.speak(items[it].value.toString()) },
        cardColor = { Color.White },
    ) { i ->
        val number = items[i]
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp),
        ) {
            Text(number.value.toString(), fontSize = 120.sp, fontWeight = FontWeight.ExtraBold)
            if (number.value in 1..20) {
                FlowRow(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally),
                ) {
                    repeat(number.value) {
                        Text(number.emoji, fontSize = 28.sp, modifier = Modifier.padding(2.dp))
                    }
                }
            }
        }
    }
}

package com.smartstart.synergy.ui.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.data.content.Content
import com.smartstart.synergy.data.content.ModuleIds
import com.smartstart.synergy.ui.components.rememberSpeaker
import com.smartstart.synergy.viewmodel.ProgressViewModel

@Composable
fun ColorsScreen(viewModel: ProgressViewModel, onBack: () -> Unit) {
    val speaker = rememberSpeaker()
    val items = Content.colors
    FlashcardScreen(
        title = "Colors",
        count = items.size,
        onBack = onBack,
        onIndexChanged = { viewModel.onModuleItemViewed(ModuleIds.COLORS, it, items.size) },
        onSpeak = { speaker.speak(items[it].label) },
        cardColor = { Color.White },
    ) { i ->
        val item = items[i]
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(item.color)
                    .border(2.dp, Color(0xFFDDDDDD), CircleShape),
            )
            Text(
                item.label,
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}

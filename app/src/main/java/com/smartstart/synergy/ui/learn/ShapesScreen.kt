package com.smartstart.synergy.ui.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
import com.smartstart.synergy.ui.components.ShapeView
import com.smartstart.synergy.ui.components.rememberSpeaker
import com.smartstart.synergy.viewmodel.ProgressViewModel

@Composable
fun ShapesScreen(viewModel: ProgressViewModel, onBack: () -> Unit) {
    val speaker = rememberSpeaker()
    val items = Content.shapes
    FlashcardScreen(
        title = "Shapes",
        count = items.size,
        onBack = onBack,
        onIndexChanged = { viewModel.onModuleItemViewed(ModuleIds.SHAPES, it, items.size) },
        onSpeak = { speaker.speak(items[it].label) },
        cardColor = { Color.White },
    ) { i ->
        val shape = items[i]
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            ShapeView(kind = shape.kind, color = shape.color, modifier = Modifier.size(180.dp))
            Text(shape.label, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

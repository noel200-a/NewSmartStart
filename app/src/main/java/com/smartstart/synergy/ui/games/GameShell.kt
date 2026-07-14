package com.smartstart.synergy.ui.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.ui.components.SectionScaffold

/** Wraps a mini game in the standard top bar and shows a prompt banner. */
@Composable
fun GameShell(
    title: String,
    prompt: String,
    score: Int,
    round: Int,
    totalRounds: Int,
    onBack: () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    SectionScaffold(title = title, onBack = onBack) { modifier ->
        Column(modifier = modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "⭐ $score    •    Round ${round.coerceAtMost(totalRounds)}/$totalRounds",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    prompt,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            content(Modifier)
        }
    }
}

@Composable
fun CelebrationDialog(score: Int, total: Int, onReplay: () -> Unit, onExit: () -> Unit) {
    AlertDialog(
        onDismissRequest = onExit,
        confirmButton = { Button(onClick = onReplay) { Text("Play again") } },
        dismissButton = { Button(onClick = onExit) { Text("Done") } },
        title = { Text("🎉 Great job!", fontWeight = FontWeight.ExtraBold) },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("🏆", fontSize = 64.sp)
                Text("You scored $score out of $total!", fontSize = 18.sp)
            }
        },
    )
}

package com.smartstart.synergy.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.R
import com.smartstart.synergy.ui.theme.Amber
import com.smartstart.synergy.ui.theme.InkDark
import com.smartstart.synergy.ui.theme.OffWhite
import com.smartstart.synergy.ui.theme.SunnyYellow

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(SunnyYellow, OffWhite))),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(28.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(32.dp))
            )
            Text(
                "Welcome!",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 40.sp,
                color = InkDark,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                "Fun learning for little stars.\nLetters, numbers, shapes, colours and animals await!",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = InkDark.copy(alpha = 0.8f),
                modifier = Modifier.padding(vertical = 16.dp),
            )
            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                ),
            ) {
                Text("Let's Start!", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            }
            Text("👨‍👩‍👧 For ages 3–6", color = Amber, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

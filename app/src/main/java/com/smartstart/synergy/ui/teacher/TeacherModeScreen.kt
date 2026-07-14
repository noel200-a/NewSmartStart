package com.smartstart.synergy.ui.teacher

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
import androidx.compose.material3.LinearProgressIndicator
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
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.ui.theme.Teal

data class ClassStats(val label: String, val value: String, val detail: String, val color: Color)

data class StudentProgress(val name: String, val modulesCompleted: Int, val totalStars: Int, val lastActive: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherModeScreen(onBack: () -> Unit) {
    val stats = listOf(
        ClassStats("Total Students", "24", "Active learners", SkyBlue),
        ClassStats("Avg. Completion", "68%", "Learning modules", Coral),
        ClassStats("Most Popular", "Alphabet", "Module this week", Grass),
        ClassStats("Top Performer", "Emma", "3★ in all modules", Pink),
        ClassStats("Engagement", "92%", "Students active today", Teal),
    )

    val students = listOf(
        StudentProgress("Emma", 5, 15, "Today"),
        StudentProgress("Liam", 4, 11, "Yesterday"),
        StudentProgress("Olivia", 3, 9, "2 days ago"),
        StudentProgress("Noah", 5, 14, "Today"),
        StudentProgress("Ava", 2, 6, "3 days ago"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Teacher Dashboard", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
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
                "Class Overview",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(stats) { stat ->
                    StatCard(stat)
                }

                item {
                    Text(
                        "Student Progress",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    )
                }

                items(students) { student ->
                    StudentCard(student)
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = SkyBlue.copy(alpha = 0.1f)),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "📌 Features Coming Soon:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(bottom = 8.dp),
                            )
                            Text(
                                "• Individual student progress tracking\n" +
                                    "• Module performance analytics\n" +
                                    "• Attendance records\n" +
                                    "• Custom assignments & quizzes\n" +
                                    "• Export reports to PDF",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(stat: ClassStats) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = stat.color.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    stat.label,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                )
                Text(
                    stat.value,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = stat.color,
                )
                Text(
                    stat.detail,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                )
            }
        }
    }
}

@Composable
private fun StudentCard(student: StudentProgress) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        student.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        "Last active: ${student.lastActive}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        "⭐ ${student.totalStars}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Grass,
                    )
                    Text(
                        "${student.modulesCompleted}/5 modules",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    )
                }
            }
            LinearProgressIndicator(
                progress = { student.modulesCompleted.toFloat() / 5f },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                color = Teal,
                trackColor = Teal.copy(alpha = 0.2f),
            )
        }
    }
}

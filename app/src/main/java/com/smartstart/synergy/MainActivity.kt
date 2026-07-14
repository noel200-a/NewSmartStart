package com.smartstart.synergy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smartstart.synergy.ui.navigation.SmartStartNavGraph
import com.smartstart.synergy.ui.theme.SmartStartTheme
import com.smartstart.synergy.viewmodel.ProgressViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = (application as SmartStartApp).progressRepository
        setContent {
            SmartStartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val progressViewModel: ProgressViewModel = viewModel(
                        factory = ProgressViewModel.Factory(repository),
                    )
                    SmartStartNavGraph(progressViewModel = progressViewModel)
                }
            }
        }
    }
}

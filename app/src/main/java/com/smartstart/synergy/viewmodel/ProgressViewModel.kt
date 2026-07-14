package com.smartstart.synergy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.smartstart.synergy.data.database.GamePlayEntity
import com.smartstart.synergy.data.database.ModuleProgressEntity
import com.smartstart.synergy.data.repository.ProgressRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProgressViewModel(private val repository: ProgressRepository) : ViewModel() {

    val moduleProgress: StateFlow<List<ModuleProgressEntity>> =
        repository.moduleProgress.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val gamePlays: StateFlow<List<GamePlayEntity>> =
        repository.gamePlays.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onModuleItemViewed(moduleId: String, itemIndex: Int, totalItems: Int) {
        viewModelScope.launch {
            repository.recordModuleProgress(moduleId, itemIndex + 1, totalItems)
        }
    }

    fun onGameFinished(gameId: String, score: Int) {
        viewModelScope.launch { repository.recordGamePlay(gameId, score) }
    }

    class Factory(private val repository: ProgressRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProgressViewModel(repository) as T
        }
    }
}

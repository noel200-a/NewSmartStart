package com.smartstart.synergy.data.repository

import com.smartstart.synergy.data.database.GamePlayEntity
import com.smartstart.synergy.data.database.ModuleProgressEntity
import com.smartstart.synergy.data.database.ProgressDao
import kotlinx.coroutines.flow.Flow

class ProgressRepository(private val dao: ProgressDao) {

    val moduleProgress: Flow<List<ModuleProgressEntity>> = dao.observeModuleProgress()
    val gamePlays: Flow<List<GamePlayEntity>> = dao.observeGamePlays()

    /**
     * Records that a child completed [itemIndex] items (out of [totalItems]) in a module.
     * Progress only ever moves forward and awards one star per third of the module completed.
     */
    suspend fun recordModuleProgress(moduleId: String, completedItems: Int, totalItems: Int) {
        val existing = dao.getModuleProgress(moduleId)
        val newCompleted = maxOf(existing?.completedItems ?: 0, completedItems).coerceAtMost(totalItems)
        val stars = when {
            totalItems == 0 -> 0
            newCompleted >= totalItems -> 3
            newCompleted >= totalItems * 2 / 3 -> 2
            newCompleted >= totalItems / 3 -> 1
            else -> 0
        }
        dao.upsertModuleProgress(
            ModuleProgressEntity(
                moduleId = moduleId,
                completedItems = newCompleted,
                totalItems = totalItems,
                stars = stars,
            )
        )
    }

    suspend fun recordGamePlay(gameId: String, score: Int) {
        dao.insertGamePlay(GamePlayEntity(gameId = gameId, score = score))
    }

    suspend fun highScore(gameId: String): Int = dao.highScore(gameId) ?: 0
}

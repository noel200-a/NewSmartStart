package com.smartstart.synergy.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Tracks how far a child has progressed within a learning module. */
@Entity(tableName = "module_progress")
data class ModuleProgressEntity(
    @PrimaryKey val moduleId: String,
    val completedItems: Int = 0,
    val totalItems: Int = 0,
    val stars: Int = 0,
    val updatedAt: Long = System.currentTimeMillis(),
)

/** One recorded play of a mini game. */
@Entity(tableName = "game_play")
data class GamePlayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val gameId: String,
    val score: Int,
    val playedAt: Long = System.currentTimeMillis(),
)

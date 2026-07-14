package com.smartstart.synergy.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {

    @Query("SELECT * FROM module_progress")
    fun observeModuleProgress(): Flow<List<ModuleProgressEntity>>

    @Query("SELECT * FROM module_progress WHERE moduleId = :moduleId LIMIT 1")
    suspend fun getModuleProgress(moduleId: String): ModuleProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertModuleProgress(entity: ModuleProgressEntity)

    @Insert
    suspend fun insertGamePlay(entity: GamePlayEntity)

    @Query("SELECT * FROM game_play ORDER BY playedAt DESC")
    fun observeGamePlays(): Flow<List<GamePlayEntity>>

    @Query("SELECT MAX(score) FROM game_play WHERE gameId = :gameId")
    suspend fun highScore(gameId: String): Int?
}

package com.smartstart.synergy

import android.app.Application
import com.smartstart.synergy.data.database.AppDatabase
import com.smartstart.synergy.data.repository.ProgressRepository

/**
 * Application entry point. Owns the singletons (database + repository) that the whole app
 * shares. A lightweight service-locator keeps Phase 1 dependency-injection-free.
 */
class SmartStartApp : Application() {
    val progressRepository: ProgressRepository by lazy {
        ProgressRepository(AppDatabase.get(this).progressDao())
    }
}

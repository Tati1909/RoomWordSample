package com.example.roomwordsample

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    // Не нужно отменять эту область видимости, так как она будет снесена в процессе
    val applicationScope = CoroutineScope(SupervisorJob())

    /**
     *     Использование by lazy, поэтому база данных и репозиторий создаются только тогда,
     *     когда они нужны, а не при запуске приложения
     */
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { WordRepository(database.wordDao()) }
}
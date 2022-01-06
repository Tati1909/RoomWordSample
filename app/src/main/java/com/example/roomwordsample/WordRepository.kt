package com.example.roomwordsample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * Репозитории служат посредником между различными источниками данных.
 * Объявляем DAO как private свойство в конструкторе.
 * Передайте DAO вместо всей базы данных, потому что вам нужен только доступ к DAO
 */
class WordRepository(private val wordDao: WordDao) {

    // Room выполняет все запросы в отдельном потоке.
    // Наблюдаемый поток уведомит наблюдателя об изменении данных.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // По умолчанию Room выполняет suspend запросы вне основного потока,
    // поэтому нам не нужно ничего реализовывать, чтобы гарантировать,
    // что мы не выполняем длительную работу с базой данных вне основного потока.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
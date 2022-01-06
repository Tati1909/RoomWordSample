package com.example.roomwordsample

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    /**
     * Использование LiveData и кеширование того, что возвращает allWords, имеет несколько преимуществ:
    - Мы можем поместить наблюдателя на данные (вместо опроса на предмет изменений) и
    обновлять пользовательский интерфейс только тогда, когда данные действительно изменяются.
    - Репозиторий полностью отделен от UI через ViewModel.
     */
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /**
     * Запуск новой сопрограммы для неблокирующей вставки данных
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
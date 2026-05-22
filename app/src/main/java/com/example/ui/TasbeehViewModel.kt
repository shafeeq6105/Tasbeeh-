package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.Counter
import com.example.data.TasbeehRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasbeehViewModel(private val repository: TasbeehRepository) : ViewModel() {
    val counters: StateFlow<List<Counter>> = repository.allCounters.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            try {
                if (repository.allCounters.first().isEmpty()) {
                    repository.insertAll(listOf(
                        Counter(title = "Subhanallah", target = 33),
                        Counter(title = "Alhamdulillah", target = 33),
                        Counter(title = "Allahu Akbar", target = 34)
                    ))
                }
            } catch (e: Exception) {
                // Ignore initial load exception
            }
        }
    }

    fun addCounter(title: String, target: Int) {
        viewModelScope.launch {
            repository.insert(Counter(title = title, target = target))
        }
    }

    fun increment(id: Int) {
        viewModelScope.launch {
            repository.increment(id)
        }
    }

    fun reset(id: Int) {
        viewModelScope.launch {
            repository.reset(id)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }
    
    fun overwriteAll(newCounters: List<Counter>) {
        viewModelScope.launch {
            repository.overwriteAll(newCounters)
        }
    }
}

class TasbeehViewModelFactory(private val repository: TasbeehRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasbeehViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TasbeehViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

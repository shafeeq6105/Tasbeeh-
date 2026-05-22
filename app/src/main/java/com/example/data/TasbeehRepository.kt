package com.example.data

import kotlinx.coroutines.flow.Flow

class TasbeehRepository(private val dao: CounterDao) {
    val allCounters: Flow<List<Counter>> = dao.getAllCounters()

    suspend fun insert(counter: Counter) = dao.insertCounter(counter)

    suspend fun insertAll(counters: List<Counter>) {
        dao.insertCounters(counters)
    }

    suspend fun increment(id: Int) = dao.incrementCount(id, System.currentTimeMillis())

    suspend fun reset(id: Int) = dao.resetCount(id, System.currentTimeMillis())

    suspend fun delete(id: Int) = dao.deleteCounter(id)
    
    suspend fun overwriteAll(counters: List<Counter>) {
        dao.deleteAll()
        dao.insertCounters(counters)
    }
}

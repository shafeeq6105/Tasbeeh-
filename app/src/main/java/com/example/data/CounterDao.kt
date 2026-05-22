package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {
    @Query("SELECT * FROM counters ORDER BY id ASC")
    fun getAllCounters(): Flow<List<Counter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounter(counter: Counter)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounters(counters: List<Counter>)

    @Query("UPDATE counters SET count = count + 1, lastUpdated = :timestamp WHERE id = :id")
    suspend fun incrementCount(id: Int, timestamp: Long)

    @Query("UPDATE counters SET count = 0, lastUpdated = :timestamp WHERE id = :id")
    suspend fun resetCount(id: Int, timestamp: Long)

    @Query("DELETE FROM counters WHERE id = :id")
    suspend fun deleteCounter(id: Int)
    
    @Query("DELETE FROM counters")
    suspend fun deleteAll()
}

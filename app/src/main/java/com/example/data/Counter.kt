package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counters")
data class Counter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val count: Int = 0,
    val target: Int = 33,
    val lastUpdated: Long = System.currentTimeMillis()
)

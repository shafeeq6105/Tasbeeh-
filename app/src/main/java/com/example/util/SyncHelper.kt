package com.example.util

import com.example.data.Counter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object SyncHelper {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(List::class.java, Counter::class.java)
    private val adapter = moshi.adapter<List<Counter>>(type)

    fun exportData(counters: List<Counter>): String {
        return adapter.toJson(counters)
    }

    fun importData(json: String): List<Counter>? {
        return try {
            adapter.fromJson(json)
        } catch (e: Exception) {
            null
        }
    }
}

package com.example

import android.app.Application
import androidx.room.Room
import com.example.data.TasbeehDatabase
import com.example.data.TasbeehRepository

class TasbeehApplication : Application() {
    lateinit var database: TasbeehDatabase
    lateinit var repository: TasbeehRepository

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            TasbeehDatabase::class.java,
            "tasbeeh_database"
        ).build()
        repository = TasbeehRepository(database.counterDao())
    }
}

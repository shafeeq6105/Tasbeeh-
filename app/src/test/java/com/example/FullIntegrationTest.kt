package com.example

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.junit.Assert.*
import com.example.data.*
import com.example.ui.*
import com.example.util.SyncHelper
import kotlinx.coroutines.runBlocking

@RunWith(AndroidJUnit4::class)
@Config(application = TasbeehApplication::class)
class FullIntegrationTest {
    @Test
    fun testEverything() {
        runBlocking {
            val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity { activity ->
            val app = activity.application as TasbeehApplication
            val repository = app.repository
            assertNotNull(repository)
            
            val counters = listOf(Counter(title = "Test", count = 5))
            val json = SyncHelper.exportData(counters)
            assertTrue(json.contains("Test"))
            
            val imported = SyncHelper.importData(json)
            assertEquals("Test", imported?.get(0)?.title)
        }
        }
    }
}

package com.example

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.junit.Assert.assertNotNull

@RunWith(AndroidJUnit4::class)
@Config(application = TasbeehApplication::class)
class StartupTest {
    @Test
    fun appStartsUp() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            assertNotNull(it)
        }
    }
}

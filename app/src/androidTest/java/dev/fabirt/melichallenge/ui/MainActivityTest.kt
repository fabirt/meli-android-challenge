package dev.fabirt.melichallenge.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent { App() }
    }

    @Test
    fun searchTest() {
        composeTestRule.onNodeWithTag("search_input").performClick()
        composeTestRule.onNodeWithTag("search_input").performTextInput("tv")
        composeTestRule.waitForIdle()
        composeTestRule.waitUntil(10_000) {
            composeTestRule.mainClock.currentTime >= 9_000
        }
        composeTestRule.onNodeWithTag("item_list").assertExists()
    }
}
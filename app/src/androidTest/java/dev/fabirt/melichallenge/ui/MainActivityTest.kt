package dev.fabirt.melichallenge.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.fabirt.melichallenge.util.waitFor
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent { App() }
    }

    @Test
    fun integrationTest() {
        composeTestRule.onNodeWithTag("search_input")
            .performClick()
            .performTextInput("tv")
        composeTestRule.waitForIdle()
        composeTestRule.waitFor(1_000)
        composeTestRule.onNodeWithTag("item_list")
            .assertExists()
            .onChildren()
            .filter(hasClickAction())
            .onFirst()
            .assert(hasTestTag("product_MCO857660267"))
            .performClick()

        composeTestRule.waitFor(1_000)
        composeTestRule.onNodeWithTag("detail_content").assertExists()
        composeTestRule.onNodeWithTag("buy_button").performClick()
        composeTestRule.waitForIdle()
    }
}
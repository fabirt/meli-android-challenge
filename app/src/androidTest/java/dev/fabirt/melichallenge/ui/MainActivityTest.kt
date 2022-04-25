package dev.fabirt.melichallenge.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.fabirt.melichallenge.util.waitUntilExists
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
        composeTestRule.waitUntilExists(hasTestTag("item_list"))
        composeTestRule.onNodeWithTag("item_list")
            .onChildren()
            .filter(hasClickAction())
            .onFirst()
            .assert(hasTestTag("product_MCO857660267"))
            .performClick()
        composeTestRule.waitUntilExists(hasTestTag("detail_content"))
        composeTestRule.onNodeWithTag("buy_button").performClick()
    }
}
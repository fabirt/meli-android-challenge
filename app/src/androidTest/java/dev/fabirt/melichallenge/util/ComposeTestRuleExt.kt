package dev.fabirt.melichallenge.util

import androidx.compose.ui.test.junit4.ComposeTestRule

fun ComposeTestRule.waitFor(delay: Long = 100) {
    val currentTime = mainClock.currentTime
    waitUntil(delay + 1_000) {
        mainClock.currentTime >= currentTime + delay
    }
}
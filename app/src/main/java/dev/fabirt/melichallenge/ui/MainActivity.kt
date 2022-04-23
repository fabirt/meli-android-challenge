package dev.fabirt.melichallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.fabirt.melichallenge.ui.screen.ProductSearchScreen
import dev.fabirt.melichallenge.ui.theme.MeliChallengeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeliChallengeTheme {
                ProductSearchScreen()
            }
        }
    }
}

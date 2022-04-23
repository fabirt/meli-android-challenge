package dev.fabirt.melichallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextInputService
import dagger.hilt.android.AndroidEntryPoint
import dev.fabirt.melichallenge.ui.component.SearchBar
import dev.fabirt.melichallenge.ui.theme.MeliChallengeTheme
import dev.fabirt.melichallenge.util.clearFocus

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var query by remember { mutableStateOf("") }

            val focusManager = LocalFocusManager.current
            val textInputService = LocalTextInputService.current

            MeliChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        SearchBar(
                            value = query,
                            onValueChange = { query = it },
                            onValueClear = { query = "" },
                            onDone = { clearFocus(focusManager, textInputService) }
                        )
                    }
                }
            }
        }
    }
}

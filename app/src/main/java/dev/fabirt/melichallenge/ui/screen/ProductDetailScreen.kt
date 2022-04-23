package dev.fabirt.melichallenge.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.fabirt.melichallenge.R
import dev.fabirt.melichallenge.ui.navigation.Navigator

@Composable
fun ProductDetailScreen(itemId: String) {
    val navController = Navigator.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.detail))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                R.string.cd_back
                            )
                        )
                    }
                }
            )
        }
    ) {

    }
}
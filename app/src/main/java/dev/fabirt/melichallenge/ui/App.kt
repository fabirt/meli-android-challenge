package dev.fabirt.melichallenge.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.fabirt.melichallenge.ui.navigation.Destination
import dev.fabirt.melichallenge.ui.navigation.Navigator
import dev.fabirt.melichallenge.ui.navigation.ProvideNavHostController
import dev.fabirt.melichallenge.ui.screen.ProductDetailScreen
import dev.fabirt.melichallenge.ui.screen.ProductSearchScreen
import dev.fabirt.melichallenge.ui.theme.MeliChallengeTheme

@Composable
fun App() {
    MeliChallengeTheme {
        ProvideNavHostController {
            NavHost(
                navController = Navigator.current,
                startDestination = Destination.SEARCH
            ) {
                composable(Destination.SEARCH) { ProductSearchScreen() }
                composable(Destination.DETAIL) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")!!
                    ProductDetailScreen(id)
                }
            }
        }
    }
}
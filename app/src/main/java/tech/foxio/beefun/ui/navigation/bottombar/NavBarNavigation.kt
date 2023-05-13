package tech.foxio.beefun.ui.navigation.bottombar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.foxio.beefun.ui.navigation.ScreenList
import tech.foxio.beefun.ui.screens.account.screen.AccountScreen
import tech.foxio.beefun.ui.screens.home.screen.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun NavBarNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = ScreenList.HomeScreen.Route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(ScreenList.HomeScreen.Route) {
            HomeScreen(navController = navController)
        }
        composable(ScreenList.AccountScreen.Route) {
            AccountScreen(navController = navController)
        }
    }
}

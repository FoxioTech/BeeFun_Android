package tech.foxio.beefun.ui.navigation.navgraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import tech.foxio.beefun.ui.navigation.SPLASH_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.ScreenList
import tech.foxio.beefun.ui.screens.splash.screen.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navController: NavController
){
    navigation(startDestination = ScreenList.SplashScreen.Route, route = SPLASH_GRAPH_ROUTE) {
        composable(route = ScreenList.SplashScreen.Route) {
            SplashScreen(navController = navController, hiltViewModel())
        }
    }
}
package tech.foxio.beefun.ui.navigation.navgraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import tech.foxio.beefun.ui.navigation.AUTH_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.ScreenList
import tech.foxio.beefun.ui.screens.auth.authOptions.screen.AuthOptionsScreen
import tech.foxio.beefun.ui.screens.auth.login.screen.LoginScreen
import tech.foxio.beefun.ui.screens.auth.register.screen.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController
){
    navigation(startDestination = ScreenList.AuthOptionsScreen.Route, route = AUTH_GRAPH_ROUTE) {
        composable(route = ScreenList.AuthOptionsScreen.Route) {
            AuthOptionsScreen(navController = navController, hiltViewModel())
        }
        composable(route = ScreenList.LoginScreen.Route) {
            LoginScreen(navController = navController, hiltViewModel())
        }
        composable(route = ScreenList.RegisterScreen.Route) {
            RegisterScreen(navController = navController, hiltViewModel())
        }
    }
}
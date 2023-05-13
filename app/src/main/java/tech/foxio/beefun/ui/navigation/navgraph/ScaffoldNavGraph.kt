package tech.foxio.beefun.ui.navigation.navgraph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tech.foxio.beefun.ui.navigation.SCAFFOLD_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.ScreenList
import tech.foxio.beefun.ui.screens.mian.screen.MainScreen

@ExperimentalMaterial3Api
fun NavGraphBuilder.scaffoldNavGraph(
    navController: NavController
){
    navigation(startDestination = ScreenList.MainScreen.Route,SCAFFOLD_GRAPH_ROUTE){
        composable(ScreenList.MainScreen.Route){
            MainScreen(navControllerScaffold = navController)
        }
    }
}
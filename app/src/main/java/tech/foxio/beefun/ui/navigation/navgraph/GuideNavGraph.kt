package tech.foxio.beefun.ui.navigation.navgraph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import tech.foxio.beefun.ui.navigation.GUIDE_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.ScreenList
import tech.foxio.beefun.ui.screens.auth.guide.screen.GuideScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.guideNavGraph(
    navController: NavController
){
    navigation(startDestination = ScreenList.GuideScreen.Route, route = GUIDE_GRAPH_ROUTE) {
        composable(route = ScreenList.GuideScreen.Route) {
            GuideScreen(navController = navController, hiltViewModel())
        }
    }
}
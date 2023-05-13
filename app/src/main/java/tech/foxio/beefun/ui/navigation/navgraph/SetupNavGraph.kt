package tech.foxio.beefun.ui.navigation.navgraph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import tech.foxio.beefun.ui.navigation.ROOT_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.SPLASH_GRAPH_ROUTE

@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SPLASH_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {
        authNavGraph(navController = navController)
        guideNavGraph(navController = navController)
        splashNavGraph(navController = navController)
        scaffoldNavGraph(navController = navController)
    }
}
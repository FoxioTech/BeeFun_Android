package tech.foxio.beefun.ui.screens.mian.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.foxio.beefun.ui.navigation.bottombar.NavBarNavigation
import tech.foxio.beefun.ui.navigation.bottombar.NavNavigation

@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    navControllerScaffold: NavController
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavNavigation(navController = navController)
        }
    ) {
        NavBarNavigation(navController = navController,it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewMainScreen(){
    MainScreen(navControllerScaffold = rememberNavController())
}
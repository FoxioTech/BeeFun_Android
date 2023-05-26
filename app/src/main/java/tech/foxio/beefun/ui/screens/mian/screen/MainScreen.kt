package tech.foxio.beefun.ui.screens.mian.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
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
        //多级嵌套Scaffold导致PaddingValues不生效，需要手动排除statusBars
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.statusBars),
        bottomBar = {
            NavNavigation(navController = navController)
        },
    ) {
        NavBarNavigation(navController = navController, it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(navControllerScaffold = rememberNavController())
}
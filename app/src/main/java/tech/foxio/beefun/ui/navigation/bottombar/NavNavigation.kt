package tech.foxio.beefun.ui.navigation.bottombar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import tech.foxio.beefun.ui.navigation.ScreenList

@Composable
fun NavNavigation(navController: NavHostController) {
    val navBarItems = listOf(
        ScreenList.HomeScreen,
        ScreenList.AccountScreen
    )
    NavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        navBarItems.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector =
                        if(currentDestination?.hierarchy?.any { it.route == item.Route } == true)
                            item.SelectedIcon!!
                        else
                            item.UnselectedIcon!!,
                        contentDescription = stringResource(id = item.Title!!),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.Title!!),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.Route } == true,
                onClick = {
                    navController.navigate(item.Route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

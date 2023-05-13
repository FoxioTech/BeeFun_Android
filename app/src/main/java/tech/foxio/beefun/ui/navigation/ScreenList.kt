package tech.foxio.beefun.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import tech.foxio.beefun.R

const val ROOT_GRAPH_ROUTE = "root"
const val SPLASH_GRAPH_ROUTE = "splash"
const val GUIDE_GRAPH_ROUTE = "guide"
const val AUTH_GRAPH_ROUTE = "auth"
const val SCAFFOLD_GRAPH_ROUTE = "scaffold"
const val HOME_GRAPH_ROUTE = "home"

sealed class ScreenList(
    val Route: String,
    val Title: Int? = null,
    val SelectedIcon: ImageVector? = null,
    val UnselectedIcon: ImageVector? = null,

) {
    object SplashScreen : ScreenList("Splash_Screen")
    object GuideScreen : ScreenList("Guide_Screen")
    object AuthOptionsScreen : ScreenList("AuthOptions_Screen")
    object LoginScreen : ScreenList("Login_Screen")
    object RegisterScreen : ScreenList("Register_Screen")
    object MainScreen : ScreenList("Main_Screen")
    object HomeScreen : ScreenList("Home_Screen", R.string.Home, Icons.Filled.Home, Icons.Outlined.Home)
    object AccountScreen : ScreenList("Account_Screen", R.string.Account, Icons.Filled.Person, Icons.Outlined.Person)
}
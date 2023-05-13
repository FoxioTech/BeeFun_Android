package tech.foxio.beefun.ui.screens.account.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.foxio.beefun.ui.screens.home.viewmodel.HomeViewModel

@ExperimentalMaterial3Api
@Composable
fun AccountScreen(
    navController : NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                windowInsets = WindowInsets(0,0,0,0),
                title = {
                    Text(
                        "Dashboard",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        },
        content = {
            println(it)
        }
    )
}
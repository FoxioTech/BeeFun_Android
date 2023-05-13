package tech.foxio.beefun.ui.screens.splash.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.foxio.beefun.ui.components.Full_Logo
import tech.foxio.beefun.ui.navigation.AUTH_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.GUIDE_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.SCAFFOLD_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.SPLASH_GRAPH_ROUTE
import tech.foxio.beefun.ui.screens.splash.viewmodel.SplashViewModel
import tech.foxio.beefun.util.AppConfig
import tech.foxio.beefun.util.LogUtil.logd
import tech.foxio.beefun.util.internetCheck

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    if (!AppConfig.Debug) {
        DebugUI(navController)
    } else {
        ReleaseUI(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReleaseUI(navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        logd("SplashScreen:AppStart")
        //延时
        kotlinx.coroutines.delay(2000)
        if (internetCheck()) {
            logd("SplashScreen:NetworkAvailable")
            navController.navigate(GUIDE_GRAPH_ROUTE) {
                popUpTo(SPLASH_GRAPH_ROUTE) {
                    inclusive = true
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Full_Logo()
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DebugUI(navController: NavController) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Button(
                onClick = {
                    navController.navigate(SCAFFOLD_GRAPH_ROUTE) {
                        popUpTo(SPLASH_GRAPH_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Go to SCAFFOLD")
            }

            Button(
                onClick = {
                    navController.navigate(AUTH_GRAPH_ROUTE) {
                        popUpTo(SPLASH_GRAPH_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Go to AUTH")
            }
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(navController = NavController(LocalContext.current))
}
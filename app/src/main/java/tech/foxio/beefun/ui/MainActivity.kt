package tech.foxio.beefun.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.foxio.beefun.ui.navigation.navgraph.SetupNavGraph
import tech.foxio.beefun.ui.theme.BeeFunTheme

@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeFunTheme{
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
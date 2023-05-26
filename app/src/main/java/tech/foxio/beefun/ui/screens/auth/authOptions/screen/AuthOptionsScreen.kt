package tech.foxio.beefun.ui.screens.auth.authOptions.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.foxio.beefun.R
import tech.foxio.beefun.ui.components.Full_Logo
import tech.foxio.beefun.ui.navigation.ScreenList
import tech.foxio.beefun.ui.screens.auth.login.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthOptionsScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(0.1f))
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.business_growth),
                        contentDescription = null
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Full_Logo()
                }
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    text = "Create game servers quickly and stably",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    text = "Welcome to Beefun",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Button(
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        navController.navigate(ScreenList.LoginScreen.Route)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 18.dp, end = 18.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Log In",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        navController.navigate(ScreenList.RegisterScreen.Route)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 18.dp, end = 18.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
    )
}

@Preview
@Composable
fun AuthOptionsScreenPreview() {
    AuthOptionsScreen(navController = rememberNavController())
}
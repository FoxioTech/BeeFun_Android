package tech.foxio.beefun.ui.screens.auth.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.foxio.beefun.R
import tech.foxio.beefun.ui.components.AuthTopAppBar
import tech.foxio.beefun.ui.navigation.SCAFFOLD_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.ScreenList
import tech.foxio.beefun.ui.screens.auth.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val pwdVisibilityState = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            AuthTopAppBar(navController = navController, "Hello", "there!")
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign in to continue",
                    modifier = Modifier
                        .align(Alignment.Start),
                )
                Spacer(modifier = Modifier.height(60.dp))
                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = {
                        emailState.value = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.Account),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null
                        )
                    },
                    shape = MaterialTheme.shapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = passwordState.value,
                    onValueChange = {
                        passwordState.value = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.Password))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                pwdVisibilityState.value = !pwdVisibilityState.value
                            }
                        ) {
                            Icon(
                                imageVector =
                                if (pwdVisibilityState.value) {
                                    Icons.Filled.Visibility
                                } else {
                                    Icons.Filled.VisibilityOff
                                },
                                contentDescription = null
                            )
                        }
                    },
                    visualTransformation =
                    if (pwdVisibilityState.value) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    shape = MaterialTheme.shapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.forget_password),
                        modifier = Modifier
                            .align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        navController.navigate(SCAFFOLD_GRAPH_ROUTE) {
//                            popUpTo(AUTH_GRAPH_ROUTE) {
////                                inclusive = true
//                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
//                    enabled = emailState.value.text.isNotBlank() && passwordState.value.text.isNotBlank()
                ) {
                    Text(
                        text = stringResource(id = R.string.Login),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don’t have an account?",
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Sign Up",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    navController.navigate(ScreenList.RegisterScreen.Route) {
                                        popUpTo(ScreenList.LoginScreen.Route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        /* Do something! */
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Continue with Facebook")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        /* Do something! */
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Continue with Google")
                }
            }
        }
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
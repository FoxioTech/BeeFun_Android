package tech.foxio.beefun.ui.screens.account.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.outlined.Fullscreen
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import tech.foxio.beefun.ui.components.HeadAppTopBarBack
import tech.foxio.beefun.ui.screens.home.viewmodel.HomeViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentDateTimeAsString(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return currentDateTime.format(formatter)
}

@ExperimentalMaterial3Api
@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val consoleState = rememberLazyListState()
    LaunchedEffect(Unit) {
        consoleState.scrollToItem(index = 99)
    }
    val consoleInputState = remember { mutableStateOf(TextFieldValue()) }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        //移除底部多余的Padding
        contentWindowInsets = WindowInsets(0,0,0,0),
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HeadAppTopBarBack(
                Title = "Console Manager",
                scrollBehavior = scrollBehavior,
                navController = navController
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 18.dp)
            ) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.extraLarge,
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(18.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .height(250.dp)
                                    .fillMaxWidth()
                                    .padding(bottom = 18.dp),
                                state = consoleState,
                                content = {
                                    items(100) {
                                        Text(
                                            text = "[${getCurrentDateTimeAsString()}] [Worker-Main-9/INFO]: Preparing spawn area: 0%",
                                            style = MaterialTheme.typography.labelSmall,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            color = Color.White
                                        )
                                    }
                                }
                            )
                            TextField(
                                label = {
                                    Text(
                                        text = "Console Input",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                singleLine = true,
                                value = consoleInputState.value,
                                onValueChange = {
                                    consoleInputState.value = it
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                shape = MaterialTheme.shapes.large,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Fullscreen,
                                        contentDescription = null
                                    )
                                },
                                trailingIcon = {
                                    Surface(
                                        modifier = Modifier
                                            .padding(end = 10.dp),
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = MaterialTheme.shapes.medium,
                                        onClick = { },
                                        content = {
                                            Row(
                                                modifier = Modifier
                                                    .padding(horizontal = 10.dp, vertical = 5.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.NavigateNext,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(24.dp)
                                                )
                                            }
                                        }
                                    )
                                },
                            )
                        }
                    }
                }
                item {
                    CommandHistoryUI()
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(30) {
                    CommandHistoryItem()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    )
}

@Composable
private fun CommandHistoryItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
    ) {
        ListItem(
            tonalElevation = 1.dp,
            headlineContent = {
                Text(
                    maxLines = 1,
                    text = "vi config",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            supportingContent = {
                Text(
                    text = "13 minutes ago",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingContent = {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Filled.Code,
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        )
    }
}

@Composable
private fun CommandHistoryUI() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Command History",
                style = MaterialTheme.typography.titleMedium,
            )
            Surface(
                tonalElevation = 3.dp,
                shape = MaterialTheme.shapes.large,
                onClick = { },
                content = {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Icon(
                            Icons.Filled.NavigateNext,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AccountScreenPreview() {
    AccountScreen(navController = rememberNavController())
}
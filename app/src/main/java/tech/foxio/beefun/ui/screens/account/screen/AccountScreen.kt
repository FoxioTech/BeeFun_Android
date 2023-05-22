package tech.foxio.beefun.ui.screens.account.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import tech.foxio.beefun.ui.components.HeadAppTopBarBack
import tech.foxio.beefun.ui.screens.home.viewmodel.HomeViewModel

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
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = { HeadAppTopBarBack(Title = "Console Manage",scrollBehavior = scrollBehavior,navController = navController) },
        content = { PaddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(PaddingValues)
                    .padding(horizontal = 18.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            shape = MaterialTheme.shapes.extraLarge,
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 18.dp)
                                    .padding(top = 18.dp, bottom = 35.dp)
                            ) {
                                LazyColumn(
                                    state = consoleState,
                                    content = {
                                        items(100) {
                                            Text(
                                                text = "[20:23:50] [Worker-Main-9/INFO]: Preparing spawn area: 0%",
                                                style = MaterialTheme.typography.labelSmall,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                color = Color.White
                                            )
                                        }
                                    }
                                )
                            }
                        }
                        TextField(
                            singleLine = true,
                            value = consoleInputState.value,
                            onValueChange = {
                                consoleInputState.value = it
                            },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Code,
                                    contentDescription = null
                                )
                            },
                            trailingIcon = {
                                Surface(
                                    modifier = Modifier
                                        .padding(end = 10.dp),
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = MaterialTheme.shapes.large,
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
                            shape = MaterialTheme.shapes.extraLarge,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            modifier = Modifier
                                .width(300.dp)
                                .padding(top = 225.dp)
                                .align(Alignment.BottomCenter)
                        )
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
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Command History",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(0.8f)
            )
            Surface(
                tonalElevation = 3.dp,
                shape = MaterialTheme.shapes.large,
                onClick = { },
                content = {
                    Row(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .height(30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Icon(
                            imageVector = Icons.Default.NavigateNext,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
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
package tech.foxio.beefun.ui.screens.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.foxio.beefun.R
import tech.foxio.beefun.ui.components.HeadAppTopBar
import tech.foxio.beefun.ui.screens.home.viewmodel.HomeViewModel

data class OverviewItem(
    val icon: ImageVector,
    val title: String,
    val route: String,
    val backgroundColor: Color,
    val iconColor: Color,
)

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        //移除底部多余的Padding
        contentWindowInsets = WindowInsets(0,0,0,0),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            HeadAppTopBar(title = "Good Morning\nCjiio")
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 18.dp),
            ) {
                items(5) {
                    ServerItem()
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    ActivityLogListUI()
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(10) {
                    ActivityItem()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    )
}

@Composable
private fun ActivityItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
    ) {
        ListItem(
            tonalElevation = 1.dp,
            headlineContent = {
                Text(
                    text = "Minecraft",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            supportingContent = {
                Text(
                    text = "8:00 AM",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingContent = {
                Surface(
                    onClick = { },
                    modifier = Modifier
                        .size(30.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            },
            leadingContent = {
                Image(
                    painter = painterResource(id = R.drawable.minecraft),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                )
            }
        )
    }
}

@Composable
private fun ActivityLogListUI() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Activity Log",
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PreviewServerItem() {
    ServerItem()
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PreviewActivityItem() {
    ActivityItem()
}

@Composable
@ExperimentalMaterial3Api
fun ServerItem() {
    var openServerInfoSheet by rememberSaveable { mutableStateOf(false) }
    val infoSkipPartiallyExpanded by remember { mutableStateOf(false) }
    val serverInfoSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = infoSkipPartiallyExpanded
    )
    var openServerOverviewSheet by rememberSaveable { mutableStateOf(false) }
    val overviewSkipPartiallyExpanded by remember { mutableStateOf(false) }
    val serverOverviewSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = overviewSkipPartiallyExpanded
    )
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
    ) {
        ListItem(
            modifier = Modifier,
            tonalElevation = 5.dp,
            headlineContent = {
                Text(
                    maxLines = 1,
                    text = "Minecraft",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            supportingContent = {
                Text(
                    text = "8:00 AM",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingContent = {
                Row(
                    modifier = Modifier
                        .weight(0.4f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Surface(
                        onClick = { openServerInfoSheet = !openServerInfoSheet },
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 5.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Button(
                        modifier = Modifier,
                        onClick = {
                            openServerOverviewSheet = !openServerOverviewSheet
                        }
                    ) {
                        Text(
                            text = "Manage",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1
                        )
                    }
                }
            },
            leadingContent = {
                Row(
                    modifier = Modifier
                        .weight(0.2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        modifier = Modifier
                            .height(50.dp)
                            .width(5.dp),
                        color = Color(0xFF1B7A00),
                        shape = MaterialTheme.shapes.small,
                        content = {}
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.minecraft),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
            }
        )
    }
    if (openServerInfoSheet) {
        ModalBottomSheet(
            windowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.statusBars),
            onDismissRequest = { openServerInfoSheet = false },
            sheetState = serverInfoSheetState,
        ) {
            ServerInfo()
        }
    }
    val overviewIconsList = listOf(
        OverviewItem(
            Icons.Filled.Code,
            "Console",
            "console",
            Color(0xFFf6eadd),
            Color(0xFFffc07a)
        ),
        OverviewItem(
            Icons.Filled.Description,
            "Files",
            "data",
            Color(0xFFffefeb),
            Color(0xFFff6740)
        ),
        OverviewItem(
            Icons.Filled.Event,
            "Database",
            "plugins",
            Color(0xFFdff0d4),
            Color(0xFF8ee04e)
        ),
        OverviewItem(
            Icons.Filled.Info,
            "Schedules",
            "about",
            Color(0xFFf6dcde),
            Color(0xFFff7d7d)
        ),
        OverviewItem(
            Icons.Filled.Code,
            "BuckUps",
            "console",
            Color(0xFFc8dbf8),
            Color(0xFF1c78ff)
        ),
        OverviewItem(
            Icons.Filled.DataObject,
            "Network",
            "data",
            Color(0xFFffefeb),
            Color(0xFFff6740)
        ),
        OverviewItem(
            Icons.Filled.LastPage,
            "StartUp",
            "plugins",
            Color(0xFFdff0d4),
            Color(0xFF8ee04e)
        ),
        OverviewItem(
            Icons.Filled.Info,
            "Settings",
            "about",
            Color(0xFFf6dcde),
            Color(0xFFff7d7d)
        ),
        OverviewItem(
            Icons.Filled.Info,
            "Activity",
            "about",
            Color(0xFFc8dbf8),
            Color(0xFF1c78ff)
        ),
    )
    if (openServerOverviewSheet) {
        ModalBottomSheet(
            windowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.statusBars),
            onDismissRequest = { openServerOverviewSheet = false },
            sheetState = serverOverviewSheetState,
        ) {
            ServerOverview(overviewIconsList = overviewIconsList)
        }
    }
}

@Composable
fun ServerOverview(overviewIconsList: List<OverviewItem>) {
    Column(
        modifier = Modifier
            .padding(horizontal = 18.dp)
    ) {
        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleLarge,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
        ) {
            items(overviewIconsList.size) {
                ServerOverviewItem(overviewIconsList[it])
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {

                }
            ) {
                Text(
                    text = "Start",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                onClick = {

                }
            ) {
                Text(
                    text = "Reboot",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                onClick = {

                }
            ) {
                Text(
                    text = "Stop",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun ServerOverviewItem(overviewItem: OverviewItem) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .size(60.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(
                containerColor = overviewItem.backgroundColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = overviewItem.icon,
                    contentDescription = "Localized description",
                    tint = overviewItem.iconColor
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = overviewItem.title,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PreviewServerOverview() {
    val overviewIconsList = listOf(
        OverviewItem(
            Icons.Filled.Code,
            "Console",
            "console",
            Color(0xFFf6eadd),
            Color(0xFFffc07a)
        ),
        OverviewItem(
            Icons.Filled.Description,
            "Files",
            "data",
            Color(0xFFffefeb),
            Color(0xFFff6740)
        ),
        OverviewItem(
            Icons.Filled.Event,
            "Database",
            "plugins",
            Color(0xFFdff0d4),
            Color(0xFF8ee04e)
        ),
        OverviewItem(
            Icons.Filled.Info,
            "Schedules",
            "about",
            Color(0xFFf6dcde),
            Color(0xFFff7d7d)
        ),
        OverviewItem(
            Icons.Filled.Code,
            "BuckUps",
            "console",
            Color(0xFFc8dbf8),
            Color(0xFF1c78ff)
        ),
        OverviewItem(
            Icons.Filled.DataObject,
            "Network",
            "data",
            Color(0xFFffefeb),
            Color(0xFFff6740)
        ),
        OverviewItem(
            Icons.Filled.LastPage,
            "StartUp",
            "plugins",
            Color(0xFFdff0d4),
            Color(0xFF8ee04e)
        ),
        OverviewItem(
            Icons.Filled.Info,
            "Settings",
            "about",
            Color(0xFFf6dcde),
            Color(0xFFff7d7d)
        ),
        OverviewItem(
            Icons.Filled.Info,
            "Activity",
            "about",
            Color(0xFFc8dbf8),
            Color(0xFF1c78ff)
        ),
    )
    ServerOverview(overviewIconsList)
}

@Composable
private fun ServerInfo() {
    Column(
        modifier = Modifier
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Server Info",
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
        ) {
            Surface(
                modifier = Modifier,
                tonalElevation = 3.dp
            ) {
                Column(modifier = Modifier) {
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Status",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF1B7A00),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Running",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "IP Address",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = "172.31.135.2:30000",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Run Time",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = "20d 22h 18m",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
        ) {
            Surface(
                modifier = Modifier,
                tonalElevation = 3.dp
            ) {
                Column(modifier = Modifier) {
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "CPU",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = "8.45%/300%",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Memory",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = "4.18 GiB/∞",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Storage Space",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = "122.21 MiB/∞",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
        ) {
            Surface(
                modifier = Modifier,
                tonalElevation = 3.dp
            ) {
                Column(modifier = Modifier) {
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Network(Input)",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = "105.45 MiB",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Network(Output)",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = "105.47 MiB",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PreviewServerInfo() {
    ServerInfo()
}
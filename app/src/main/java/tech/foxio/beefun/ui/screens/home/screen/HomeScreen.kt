package tech.foxio.beefun.ui.screens.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
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

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            HeadAppTopBar(Title = "Good Morning\nCjiio")
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 18.dp),
            ) {
                items(20) {
                    ServerItem()
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    )
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
        Surface(
            modifier = Modifier,
            tonalElevation = 3.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                Spacer(modifier = Modifier.width(5.dp))
                Column(
                    modifier = Modifier
                        .weight(0.4f),
                ) {
                    Text(
                        maxLines = 1,
                        text = "Minecraft",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = "8:00 AM",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(0.4f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = { openServerInfoSheet = !openServerInfoSheet },
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
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
            }
        }
    }
    if (openServerInfoSheet) {
        ModalBottomSheet(
            onDismissRequest = { openServerInfoSheet = false },
            sheetState = serverInfoSheetState,
        ) {
            ServerInfo()
        }
    }
    if (openServerOverviewSheet) {
        ModalBottomSheet(
            onDismissRequest = { openServerOverviewSheet = false },
            sheetState = serverOverviewSheetState,
        ) {
            ServerOverview()
        }
    }
}

@Composable
fun ServerOverview() {
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
            items(8) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        modifier = Modifier,
                        shape = MaterialTheme.shapes.extraLarge,
                    ) {
                        Surface(
                            modifier = Modifier
                                .padding(20.dp),
                            shape = MaterialTheme.shapes.large,
                            color = Color.Transparent
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DataObject,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Console",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PreviewServerOverview() {
    ServerOverview()
}

@Composable
private fun ServerInfo() {
    Column(
        modifier = Modifier
            .padding(horizontal = 18.dp)
    ) {
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
                            text = "105.46 MiB",
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
package tech.foxio.beefun.ui.screens.auth.guide.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import tech.foxio.beefun.R
import tech.foxio.beefun.ui.navigation.AUTH_GRAPH_ROUTE
import tech.foxio.beefun.ui.navigation.GUIDE_GRAPH_ROUTE
import tech.foxio.beefun.ui.screens.home.viewmodel.HomeViewModel

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Composable
fun GuideScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(initialPage = 0)
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val guideScreenList = listOf(
        GuideScreenItem(
            R.drawable.business_growth,
            "Business Growth",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ),
        GuideScreenItem(
            R.drawable.business_promotion,
            "Business Promotion",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ),
        GuideScreenItem(
            R.drawable.target_audience,
            "Target Audience",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ),
    )
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(0.1f))
                HorizontalPager(
                    state = pagerState,
                    count = guideScreenList.size,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { page ->
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = guideScreenList[page].image),
                            contentDescription = null
                        )
                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    indicatorWidth = 20.dp,
                    indicatorHeight = 5.dp,
                    spacing = 10.dp,
                    activeColor = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    text = guideScreenList[pagerState.currentPage].title,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = guideScreenList[pagerState.currentPage].description,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.weight(0.1f))
                val animationScope = rememberCoroutineScope()
                Button(
                    onClick = {
                        animationScope.launch {
                            if (pagerState.currentPage == 2) {
                                openBottomSheet = !openBottomSheet
                                return@launch
                            }
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 18.dp, end = 18.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Next",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
            if (openBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { openBottomSheet = false },
                    sheetState = bottomSheetState,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 18.dp)
                    ) {
                        Text(
                            text = "REQUEST ACCESS",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            text = "Need your Permission",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn {
                            items(4) {
                                PermissionItem(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun PermissionItem(navController: NavController) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        Surface(
            modifier = Modifier,
            tonalElevation = 2.dp
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
                    Image(
                        painter = painterResource(id = R.drawable.minecraft),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column(
                    modifier = Modifier
                        .weight(0.5f),
                ) {
                    Text(
                        maxLines = 1,
                        text = "Camera",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = "Allow app for use  to take picture.Allow app for use  to take picture.All",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(0.3f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = Modifier,
                        onClick = {
                            navController.navigate(AUTH_GRAPH_ROUTE)
                            {
                                popUpTo(GUIDE_GRAPH_ROUTE) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Text(
                            text = "Allow",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

private data class GuideScreenItem(
    val image: Int,
    val title: String,
    val description: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewGuideScreen() {
    GuideScreen(navController = NavController(LocalContext.current))
}

@Preview
@Composable
fun PreviewPermissionItem() {
    PermissionItem(navController = NavController(LocalContext.current))
}
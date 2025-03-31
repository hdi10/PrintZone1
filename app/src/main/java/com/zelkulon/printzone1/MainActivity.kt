package com.zelkulon.printzone1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zelkulon.printzone1.ui.theme.PrintZone1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrintZone1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyComposeScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyComposeScreen() {
    val tabTitles = listOf("Monday", "Tuesday", "Wednesday")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var selectedBottomItem by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = selectedBottomItem == "home",
                    onClick = { selectedBottomItem = "home" },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                BottomNavigationItem(
                    selected = selectedBottomItem == "settings",
                    onClick = { selectedBottomItem = "settings" },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {

            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(title) }
                    )
                }
            }

            HorizontalPager(
                count = tabTitles.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> MondayScreen()
                    1 -> TuesdayScreen()
                    2 -> WednesdayScreen()
                }
            }
        }
    }
}

@Composable fun MondayScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("This is Monday")
    }
}

@Composable fun TuesdayScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("This is Tuesday")
    }
}

@Composable fun WednesdayScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("This is Wednesday")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrintZone1Theme {
        Greeting("Android")
    }
}
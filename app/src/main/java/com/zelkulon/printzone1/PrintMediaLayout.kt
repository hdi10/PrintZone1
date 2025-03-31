package com.zelkulon.printzone1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.zelkulon.printzone1.ui.theme.PrintZone1Theme
import kotlinx.coroutines.launch

import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


private val mediaTabs = listOf(
    "📰 Zeitung", "🗞️ Artikel", "📖 Magazin", "📚 Broschüre", "🧾 Flyer", "📑 Katalog",
    "✉️ Brief", "📬 Postkarte", "📇 Visitenkarte", "📦 Verpackung", "📂 Mappe",
    "🏷️ Etikett", "📄 PDF", "🖼️ Bild", "🎨 Layout", "🖋️ Typografie", "📏 Format",
    "🔖 Lesezeichen", "🧷 Heftung", "🧩 Infografik", "📊 Diagramm", "🧮 Raster",
    "🕸️ Designnetz", "🔲 Muster", "🧭 Orientierung", "📐 Linien", "📅 Kalender"
)



@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun PrintMediaTabLayout() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 8.dp
        ) {
            mediaTabs.forEachIndexed { index, label ->
                val (icon, title) = label.split(" ", limit = 2).let {
                    it.first() to it.getOrElse(1) { "" }
                }

                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(icon, textAlign = TextAlign.Center)
                            Text(title, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            count = mediaTabs.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) { page ->
            PrintMediaTabContent(tabLabel = mediaTabs[page])
        }
    }
}

@Composable
fun PrintMediaTabContent(tabLabel: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Inhalt für: $tabLabel",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun PrintMediaPreview() {
    PrintZone1Theme {
        PrintMediaTabLayout()
    }
}

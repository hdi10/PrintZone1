package com.zelkulon.printzone1

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

data class MediaTab(val title: String, @DrawableRes val imageRes: Int)

private val mediaTabs1 = listOf(
    "📰 Zeitung", "🗞️ Artikel", "📖 Magazin", "📚 Broschüre", "🧾 Flyer", "📑 Katalog",
    "✉️ Brief", "📬 Postkarte", "📇 Visitenkarte", "📦 Verpackung", "📂 Mappe",
    "🏷️ Etikett", "📄 PDF", "🖼️ Bild", "🎨 Layout", "🖋️ Typografie", "📏 Format",
    "🔖 Lesezeichen", "🧷 Heftung", "🧩 Infografik", "📊 Diagramm", "🧮 Raster",
    "🕸️ Designnetz", "🔲 Muster", "🧭 Orientierung", "📐 Linien", "📅 Kalender"
)

private val mediaTabs = listOf(
    "📦 Aufsteller",
    "🎌 Banner",
    "🚩 Fahnen",
    "🛡️ Hygieneschutz",
    "🧻 Klebefolie",
    "💡 Leuchtkästen",
    "🧱 Messewände",
    "📢 Plakate",
    "📰 Printmedien",
    "📜 RollUp",
    "🚧 Werbeschilder"
)

private val mediaTabs2 = listOf(
    MediaTab("📦 Aufsteller",R.drawable.aufsteller),
    MediaTab("🗞️ Banner",R.drawable.banner),
    MediaTab("📖 Fahnen",R.drawable.fahnen),
    MediaTab("🛡️ Hygieneschutz",R.drawable.hygieneschutz),
    MediaTab("🧻 Klebefolie",R.drawable.klebefolien),
    MediaTab("💡 Leuchtkästen",R.drawable.leuchtkasten),
    MediaTab("🧱 Messewände",R.drawable.messewand),
    MediaTab("📢 Plakate",R.drawable.plakate),
    MediaTab("📰 Printmedien",R.drawable.printmedien),
    MediaTab("📜 RollUp",R.drawable.rollup),
    MediaTab("🚧 Werbeschilder",R.drawable.werbeschilder),
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun PrintMediaTabLayout() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf("Berlin") } // oder GPS-Ort später einbinden
    var deliveryOption by remember { mutableStateOf("Lieferung") }


    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        //Spacer(modifier = Modifier.height(16.dp))
        SearchLikeButton {

        }

        //Spacer(modifier = Modifier.height(128.dp))
        OrtArtWarenkorbRow(
            onWarenkorbClick = { expanded = true },
            selectedOption = deliveryOption,
            onOptionSelected = { deliveryOption = it },
            locationText = location,
            onLocationChange = { location = it }
        )




        //OrtArtWarenkorb

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "Delete")
            }
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "Save")
            }
        }




        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 8.dp
        ) {
            mediaTabs2.forEachIndexed { index, tab ->
                val (label, imageRes) = tab
                val (emoji, title) = label.split(" ", limit = 2).let {
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
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = title,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(96.dp)
                                    .padding(bottom = 4.dp)
                            )
                            Text(
                                title,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                )
            }
        }




        /*ScrollableTabRow(
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
                            Text(icon,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineLarge)
                            Text(
                                title,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                )
            }
        }*/

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

        HorizontalPager(
            count = mediaTabs.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) { page ->
            //TODO: Der Inhalt aus den Icons --> ContentLoad
            PrintMediaTabContent(tabLabel = mediaTabs[page])
        }
    }





}

//Durchsuchen Button


@Composable
fun OrtArtWarenkorbRow(
    onWarenkorbClick: () -> Unit,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    locationText: String,
    onLocationChange: (String) -> Unit
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = locationText,
            onValueChange = onLocationChange,
            label = { Text("Ort der Lieferung") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 8.dp)
        ) {
            Text(
                text = selectedOption,
                modifier = Modifier
                    .clickable { dropdownExpanded = true }
                    .padding(8.dp)
            )

            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false }
            ) {
                listOf("Lieferung", "Abholung").forEach { option ->
                    DropdownMenuItem(onClick = {
                        onOptionSelected(option)
                        dropdownExpanded = false
                    }) {
                        Text(option)
                    }
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.basket_icon_inkscape),
            contentDescription = "Warenkorb",
            modifier = Modifier
                .size(32.dp)
                .clickable { onWarenkorbClick() }
        )
    }
}

@Composable
fun SearchLikeButton(
    text: String = "Durchsuchen...",
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(56.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.magnifiericon), // Deine Lupe hier!
                contentDescription = "Search Icon",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    PrintMediaTabLayout()
}

/////////////////////////////////////////////////////////////////////////////////////
////////////////////Content -> Alle Geschäfte in der nähe //////////////////////////
//////////////////////////// ab hier --> //////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////
////////////////////Content -> Alle Geschäfte in der nähe //////////////////////////
//////////////////////////// --> bis hier /////////////////////////////////////////
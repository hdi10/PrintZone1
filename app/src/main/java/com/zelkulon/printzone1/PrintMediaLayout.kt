package com.zelkulon.printzone1

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
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
    var expanded by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf("Berlin") } // oder GPS-Ort später einbinden
    var deliveryOption by remember { mutableStateOf("Lieferung") }


    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(128.dp))
        OrtArtWarenkorbRow(
            onWarenkorbClick = { expanded = true },
            selectedOption = deliveryOption,
            onOptionSelected = { deliveryOption = it },
            locationText = location,
            onLocationChange = { location = it }
        )

        //OrtArtWarenkorb


        //Ort

        //Art

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
                            Text(
                                title,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelSmall
                            )
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
                .padding(32.dp)
        ) { page ->
            //TODO: Der Inhalt aus den Icons --> ContentLoad
            //PrintMediaTabContent(tabLabel = mediaTabs[page])
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


}
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


/////////////////////////////////////////////////////////////////////////////////////
////////////////////Content -> Alle Geschäfte in der nähe //////////////////////////
//////////////////////////// ab hier --> //////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////
////////////////////Content -> Alle Geschäfte in der nähe //////////////////////////
//////////////////////////// --> bis hier /////////////////////////////////////////

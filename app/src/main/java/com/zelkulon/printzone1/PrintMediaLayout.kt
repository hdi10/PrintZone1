package com.zelkulon.printzone1
import com.zelkulon.printzone1.port.viewmodel.PrintMediaViewModel
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.zelkulon.printzone1.core.data.remote.dto.PrintMediaDto
import com.zelkulon.printzone1.core.domain.model.PrintMedia


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
        ////////////////////

        Spacer(modifier = Modifier.height(132.dp))

        ////////////////////
        SearchLikeButton {

        }

        ////////////////////

        //Spacer(modifier = Modifier.height(128.dp))
        OrtArtWarenkorbRow(
            onWarenkorbClick = { expanded = true },
            selectedOption = deliveryOption,
            onOptionSelected = { deliveryOption = it },
            locationText = location,
            onLocationChange = { location = it }
        )



        ////////////////////
        //Warenkorb

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


        ////////////////////
        //TabRow


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

            PrintMediaTabContent(tabLabel = mediaTabs[page])

        }
    }

}

///////////////////////////////////////////////////////////////////
//////////////////////////ScrolBar Inhalt              /////////////
///////////////////////////////////////////////////////////////////

// PrintMediaTabContent → ersetzen:
@Composable
fun PrintMediaTabContent(tabLabel: String, viewModel: PrintMediaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val _medien = mutableStateListOf<PrintMediaDto>()
    val medien: List<PrintMediaDto> = _medien

    LaunchedEffect(Unit) {
        viewModel.loadPrintMediaList()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Inhalt für: $tabLabel",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrintMediaList(medien)
    }
}

@Composable
fun PrintMediaList(medien: List<PrintMediaDto>) {
    Column {
        medien.forEach {
            Text(text = "• ${it.id} – ${it.title} - ${it.description} •")
        }
    }
}

///////////////////////////////////////////////////////////////////
//Durchsuchen Button
///////////////////////////////////////////////////////////////////


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


///////////////////////////////////////////////////////////////////
/// Durchsuchen Feld    ///////////////////////////////////////////
///////////////////////////////////////////////////////////////////

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


/////////////////////////////////////////////////////////////////////////////////////
////////////////////Content -> Alle Geschäfte in der nähe //////////////////////////
//////////////////////////// ab hier --> //////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////
////////////////////Content -> Alle Geschäfte in der nähe //////////////////////////
//////////////////////////// --> bis hier /////////////////////////////////////////
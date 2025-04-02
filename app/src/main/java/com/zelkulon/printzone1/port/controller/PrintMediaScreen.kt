package com.zelkulon.printzone1.port.controller



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zelkulon.printzone1.core.domain.model.PrintMedia
import com.zelkulon.printzone1.port.viewmodel.PrintMediaListUiState
import com.zelkulon.printzone1.port.viewmodel.PrintMediaViewModel


@Composable
fun PrintMediaScreen(viewModel: PrintMediaViewModel) {
    // Den UI-State vom ViewModel als Compose-State abonnieren
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Printmedien") })
    }) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            when (uiState) {
                is PrintMediaListUiState.Loading -> {
                    // Ladeindikator zentriert anzeigen
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is PrintMediaListUiState.Success -> {
                    val list = (uiState as PrintMediaListUiState.Success).mediaList
                    if (list.isEmpty()) {
                        // Leerer Zustand
                        Text("Keine Printmedien vorhanden", modifier = Modifier.align(Alignment.Center))
                    } else {
                        // Liste der Printmedien anzeigen
                        LazyColumn {
                            items(list) { item ->
                                PrintMediaListItem(item)
                            }
                        }
                    }
                }
                is PrintMediaListUiState.Error -> {
                    val errorMessage = (uiState as PrintMediaListUiState.Error).message
                    Column(modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = errorMessage, color = MaterialTheme.colors.error)
                        Spacer(modifier = Modifier.height(8.dp))
                        // Retry-Button, um erneut zu laden
                        Button(onClick = { viewModel.loadPrintMediaList() }) {
                            Text("Erneut versuchen")
                        }
                    }
                }
            }
        }
    }
}

/** Einzelnes Listen-Item für ein Printmedium. */
@Composable
fun PrintMediaListItem(item: PrintMedia) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = item.title, style = MaterialTheme.typography.h6)
            Text(text = item.description, style = MaterialTheme.typography.body2)
        }
    }
}

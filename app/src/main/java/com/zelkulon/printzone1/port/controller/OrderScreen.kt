package com.zelkulon.printzone1.port.controller


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.zelkulon.printzone1.core.domain.model.PrintMedia
import com.zelkulon.printzone1.port.viewmodel.OrderUiState
import com.zelkulon.printzone1.port.viewmodel.OrderViewModel

@Composable
fun OrderScreen(
    viewModel: OrderViewModel,
    availablePrintMedia: List<PrintMedia>
) {
    // UI-States aus dem ViewModel beobachten
    val orderState by viewModel.orderState.collectAsState()

    // Lokale Zustände für Benutzereingaben
    var selectedMediaId by remember { mutableStateOf<Int?>(null) }
    var quantityInput by remember { mutableStateOf(TextFieldValue("1")) }
    var selectedCustomerId by remember { mutableStateOf(1) }  // Beispielhaft ein Default
    var selectedAddressId by remember { mutableStateOf(1) }   // Beispielhaft ein Default

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Neue Bestellung", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))

        // Dropdown für Printmedium-Auswahl
        Text("Printmedium:")
        DropdownMenu(
            expanded = false,
            onDismissRequest = { /* Implement Dropdown-Open/Close-Logik hier */ }
        ) {
            availablePrintMedia.forEach { media ->
                DropdownMenuItem(onClick = {
                    selectedMediaId = media.id
                }) {
                    Text(media.title)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        // Eingabefeld für Menge
        Text("Menge:")
        TextField(
            value = quantityInput,
            onValueChange = { quantityInput = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        // (Hier könnten Dropdowns oder Felder für Kunde und Adresse folgen)
        // Für Einfachheit nehmen wir an, customerId und addressId stehen fest (z.B. 1).

        Button(onClick = {
            val qty = quantityInput.text.toIntOrNull() ?: 0
            if (selectedMediaId != null && qty > 0) {
                viewModel.submitOrder(selectedMediaId!!, qty, selectedCustomerId, selectedAddressId)
            }
        }) {
            Text("Bestellung abschicken")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ausgabe basierend auf OrderState
        when (orderState) {
            is OrderUiState.Idle -> {
                // Noch keine Aktion erfolgt
            }
            is OrderUiState.Submitting -> {
                CircularProgressIndicator()
            }
            is OrderUiState.Success -> {
                val order = (orderState as OrderUiState.Success).order
                Text("Bestellung erfolgreich! ID: ${order.id}")
            }
            is OrderUiState.Error -> {
                val msg = (orderState as OrderUiState.Error).message
                Text("Fehler: $msg", color = MaterialTheme.colors.error)
            }
        }
    }
}
package com.zelkulon.printzone1
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.json.Json // ⬅️ am Anfang der Datei
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class Artist36ViewModel : ViewModel() {
    var printmedien by mutableStateOf<List<PrintMedium>>(emptyList())
        private set

    init {
        fetchPrintmedien()
    }

    private fun fetchPrintmedien() {
        viewModelScope.launch {
            try {
                val result = fetchPrintMedienFromApi() // Funktion für API-Call
                printmedien = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun fetchPrintMedienFromApi(): List<PrintMedium> {
        val client = HttpClient(CIO) // wir brauchen keinen ContentNegotiation hier
        val response = client.get("https://printzone1-app-90b795cbbc5a.herokuapp.com/printmedien")
        val responseBody = response.bodyAsText()

        val json = Json {
            ignoreUnknownKeys = true // 💡 Damit wird z.B. "titel" ignoriert, wenn nicht im Datenmodell
        }

        return json.decodeFromString(responseBody)
    }
}


package com.zelkulon.printzone1.port.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zelkulon.printzone1.core.domain.model.PrintMedia
import com.zelkulon.printzone1.core.domain.usecase.GetPrintMediaUseCase
import com.zelkulon.printzone1.port.exception.AppException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/** UI-State für die PrintMedia-Liste. */
sealed class PrintMediaListUiState {
    object Loading : PrintMediaListUiState()
    data class Success(val mediaList: List<PrintMedia>) : PrintMediaListUiState()
    data class Error(val message: String) : PrintMediaListUiState()
}

class PrintMediaViewModel(private val getPrintMediaUseCase: GetPrintMediaUseCase) : ViewModel() {

    // StateFlow für den UI-Zustand der Printmedien-Liste
    private val _uiState = MutableStateFlow<PrintMediaListUiState>(PrintMediaListUiState.Loading)
    val uiState: StateFlow<PrintMediaListUiState> = _uiState

    init {
        // Beim Initialisieren direkt Daten laden
        loadPrintMediaList()
    }

    fun loadPrintMediaList() {
        _uiState.value = PrintMediaListUiState.Loading
        viewModelScope.launch {
            try {
                val media = getPrintMediaUseCase.execute()
                if (media.isEmpty()) {
                    // Leerer Erfolgsfall (keine Daten)
                    _uiState.value = PrintMediaListUiState.Success(emptyList())
                } else {
                    _uiState.value = PrintMediaListUiState.Success(media)
                }
            } catch (e: Exception) {
                // Fehler aufgetreten (Netzwerk oder andere)
                val errorMsg = if (e is AppException) {
                    // Verwende falls möglich eine beschreibende Fehlermeldung
                    e.message ?: "Unbekannter Fehler"
                } else {
                    "Fehler beim Laden der Printmedien"
                }
                _uiState.value = PrintMediaListUiState.Error(errorMsg)
            }
        }
    }
}

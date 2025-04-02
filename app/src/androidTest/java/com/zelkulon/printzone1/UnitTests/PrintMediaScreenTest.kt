/*
package com.zelkulon.printzone1.UnitTests
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zelkulon.printzone1.core.domain.model.PrintMedia
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.zelkulon.printzone1.port.viewmodel.PrintMediaListUiState
import com.zelkulon.printzone1.port.viewmodel.PrintMediaViewModel
import com.zelkulon.printzone1.core.domain.usecase.GetPrintMediaUseCase
import com.zelkulon.printzone1.core.domain.repository.PrintMediaRepository
import com.zelkulon.printzone1.port.controller.PrintMediaScreen
import kotlinx.coroutines.flow.MutableStateFlow


@RunWith(AndroidJUnit4::class)
class PrintMediaScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun printMediaList_showsItems_whenDataAvailable() {
        // Fake-Repository und UseCase, der eine vordefinierte Liste zurückliefert
        val fakeList = listOf(PrintMedia(1, "Test Title", "Test Description"))
        val fakeRepository = object : PrintMediaRepository {
            override suspend fun getPrintMediaList(): List<PrintMedia> = fakeList
        }
        val fakeUseCase = GetPrintMediaUseCase(fakeRepository)
        // Fake-ViewModel mit überschriebener Ladelogik, um sofort den gewünschten Zustand zu setzen
        val fakeViewModel = object : PrintMediaViewModel(fakeUseCase) {
            override fun loadPrintMediaList() { */
/* Überspringt den echten Ladevorgang *//*
 }
            init {
                // Setze den UI-State direkt auf Success mit der Testliste
                @Suppress("UNCHECKED_CAST")
                (uiState as MutableStateFlow<PrintMediaListUiState>).value =
                    PrintMediaListUiState.Success(fakeList)
            }
        }

        composeTestRule.setContent {
            PrintMediaScreen(viewModel = fakeViewModel)
        }

        // Prüfen, ob der Titel des Test-Printmediums angezeigt wird
        composeTestRule.onNodeWithText("Test Title").assertExists()
    }

    @Test
    fun printMediaList_showsEmptyState_whenListEmpty() {
        val fakeRepository = object : PrintMediaRepository {
            override suspend fun getPrintMediaList(): List<PrintMedia> = emptyList()
        }
        val fakeUseCase = GetPrintMediaUseCase(fakeRepository)
        val fakeViewModel = object : PrintMediaViewModel(fakeUseCase) {
            override fun loadPrintMediaList() { */
/* keine Aktion (Override) *//*
 }
            init {
                @Suppress("UNCHECKED_CAST")
                (uiState as MutableStateFlow<PrintMediaListUiState>).value =
                    PrintMediaListUiState.Success(emptyList())
            }
        }
        composeTestRule.setContent { PrintMediaScreen(viewModel = fakeViewModel) }
        // "Keine Printmedien vorhanden" Text sollte sichtbar sein
        composeTestRule.onNodeWithText("Keine Printmedien vorhanden").assertExists()
    }

    @Test
    fun printMediaList_showsErrorState_whenErrorOccurs() {
        val fakeRepository = object : PrintMediaRepository {
            override suspend fun getPrintMediaList(): List<PrintMedia> {
                throw Exception("Network failure")
            }
        }
        val fakeUseCase = GetPrintMediaUseCase(fakeRepository)
        val fakeViewModel = object : PrintMediaViewModel(fakeUseCase) {
            override fun loadPrintMediaList() { */
/* keine Aktion (Override) *//*
 }
            init {
                @Suppress("UNCHECKED_CAST")
                (uiState as MutableStateFlow<PrintMediaListUiState>).value =
                    PrintMediaListUiState.Error("Netzwerkfehler")
            }
        }
        composeTestRule.setContent { PrintMediaScreen(viewModel = fakeViewModel) }
        // Fehlermeldung anzeigen
        composeTestRule.onNodeWithText("Netzwerkfehler").assertExists()
        // Retry-Button anzeigen
        composeTestRule.onNodeWithText("Erneut versuchen").assertExists()
    }
}*/

package com.zelkulon.printzone1.core.domain.usecase

/**
 * Anwendungslogik: Alle Prinmedien abrufen (mit Sync von Remote nach Local)
 */

import com.zelkulon.printzone1.core.data.remote.RetrofitInstance
import com.zelkulon.printzone1.core.domain.model.PrintMedia
import com.zelkulon.printzone1.core.domain.repository.PrintMediaRepository

class GetPrintMediaUseCase (private val repository: PrintMediaRepository){

    /**
     * Führt den Use Case aus.
     * Holt die Liste der Printmedien, aktualisiert den lokalen Cache und gibt die Liste zurück
     * Wirft eine Exception, wenn weder Netzwerk noch Cache eine Liste liefern (Fehlerfall).
     */

    suspend fun execute(): List<PrintMedia> {
        // Delegiert an das Repository, das intern Remote- und Local-Daten handhabt.
        return repository.getPrintMediaList()
    }
}
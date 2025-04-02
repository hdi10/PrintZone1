package com.zelkulon.printzone1.core.domain.repository

import com.zelkulon.printzone1.core.domain.model.PrintMedia

/**
 * Repository-Interface für Printmedien-Datenquellen (Remote/Local)
 */
interface PrintMediaRepository {
    /**
     * Liefert eine Liste aller Printmedien
     * Bei Verfügbarkeit werden Remote-Daten geholt und in den localen cahce übernommen
     * @throws Exception NetworkException -> falls weder Remote noch Cache verfügbar sind.
     */
    suspend fun getPrintMediaList(): List <PrintMedia>
}
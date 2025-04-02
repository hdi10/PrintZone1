package com.zelkulon.printzone1.core.data


import com.zelkulon.printzone1.core.data.local.dao.PrintMediaDao
import com.zelkulon.printzone1.core.data.local.entity.PrintMediaEntity
import com.zelkulon.printzone1.core.data.remote.ApiService
import com.zelkulon.printzone1.core.domain.model.Order
import com.zelkulon.printzone1.core.domain.model.PrintMedia
import com.zelkulon.printzone1.core.domain.repository.PrintMediaRepository
import com.zelkulon.printzone1.port.exception.AppException
import kotlinx.coroutines.flow.Flow


/**
 * Implementierung des PrintMediaRepository.
 * Nutzt Remote-API (ApiService) und lokale Datenbank (PrintMediaDao).
 */
class PrintMediaRepositoryImpl(
    private val api: ApiService,
    private val printMediaDao: PrintMediaDao
) : PrintMediaRepository {

    override suspend fun getPrintMediaList(): List<PrintMedia> {
        return try {
            // 1. Remote-Daten abrufen
            val remoteList = api.getPrintMediaList()
            val domainList = remoteList.map { it.toDomainModel() }
            // 2. Lokalen Cache aktualisieren (alte Daten löschen und neue einfügen)
            printMediaDao.deleteAll()
            val entities = domainList.map { PrintMediaEntity(it.id, it.title, it.description) }
            printMediaDao.insertAll(entities)
            // 3. Aktuelle Domain-Liste zurückgeben
            domainList
        } catch (e: Exception) {
            // Netzwerkfehler o.Ä.
            val cached = printMediaDao.getAllOnce().map {
                PrintMedia(it.id, it.title, it.description)
            }
            if (cached.isNotEmpty()) {
                // Falls Netzwerk fehlgeschlagen, aber Cache vorhanden: Cache-Daten zurück
                cached
            } else {
                // Weder Remote noch Cache verfügbar: Exception weiterwerfen
                throw AppException.NetworkException(e)
            }
        }
    }

}
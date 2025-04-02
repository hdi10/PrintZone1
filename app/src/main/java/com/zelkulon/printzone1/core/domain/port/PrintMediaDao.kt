package com.zelkulon.printzone1.core.domain.port

import com.zelkulon.printzone1.core.domain.model.PrintMedia

interface PrintMediaDao {
    suspend fun getAllPrintMedia(): List<PrintMedia>
    suspend fun insertPrintMediaList(mediaList: List<PrintMedia>)
    suspend fun clearAll()
}
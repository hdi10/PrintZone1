package com.zelkulon.printzone1.core.data.local.dummy

import com.zelkulon.printzone1.core.domain.model.PrintMedia
import com.zelkulon.printzone1.core.domain.port.PrintMediaDao

class DummyPrintMediaDao : PrintMediaDao {
    override suspend fun getAllPrintMedia(): List<PrintMedia> = emptyList()
    override suspend fun insertPrintMediaList(mediaList: List<PrintMedia>) = Unit
    override suspend fun clearAll() = Unit
}
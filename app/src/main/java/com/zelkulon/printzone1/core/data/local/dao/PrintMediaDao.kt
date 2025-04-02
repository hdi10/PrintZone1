package com.zelkulon.printzone1.core.data.local.dao

import androidx.room.*
import androidx.room.Dao
import com.zelkulon.printzone1.core.data.local.entity.PrintMediaEntity

@Dao
interface PrintMediaDao {
    /** Flow für alle Printmedien aus der lokalen DB (wird automatisch aktualisiert). */
    @Query("SELECT * FROM print_media")
    fun getAllFlow(): kotlinx.coroutines.flow.Flow<List<PrintMediaEntity>>

    /** Einmaliger Abruf aller Printmedien (z.B. für Fallback-Zugriff). */
    @Query("SELECT * FROM print_media")
    suspend fun getAllOnce(): List<PrintMediaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mediaList: List<PrintMediaEntity>)

    @Query("DELETE FROM print_media")
    suspend fun deleteAll()
}

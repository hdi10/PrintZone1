package com.zelkulon.printzone1.core.data.local.entity

import androidx.room.*

/**
 * Room-Entity für Bestellung.
 * Speichert referenzielle IDs; Foreign Keys sind hier optional.
 */
@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val localId: Int = 0,  // interner Primärschlüssel
    val serverId: Int?,      // ID vom Server (null, wenn noch nicht synchronisiert)
    val printMediaId: Int,
    val quantity: Int,
    val customerId: Int,
    val addressId: Int
)
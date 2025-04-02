package com.zelkulon.printzone1.core.data.local.entity

import androidx.room.*

@Entity(tableName = "print_media")
data class PrintMediaEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String
)

package com.zelkulon.printzone1

import kotlinx.serialization.Serializable

@Serializable
data class PrintMedium(
    val id: Long,
    val titel: String, // <- hinzugefügt
    val art: String,   // <- falls vorhanden
    val beschreibung: String = "",
    val preis: Double = 0.0
)
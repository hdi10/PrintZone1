package com.zelkulon.printzone1.core.domain.model

import android.icu.text.CaseMap.Title

/**
 * Domain-Datenmodell f. PrintMedium (z.B. Abschlussarbeiten, Roll-Ups, Poster, ...
 */
data class PrintMedia(
    val id: Int,
    val title: String,
    val description: String
)

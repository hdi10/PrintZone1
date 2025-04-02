package com.zelkulon.printzone1.core.data.remote.dto



import com.google.gson.annotations.SerializedName
import com.zelkulon.printzone1.core.domain.model.PrintMedia


/**
 * DTO für Printmedium – Abbild der JSON-Struktur vom Backend.
 */
data class PrintMediaDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
) {
    /** Konvertierung des DTO ins Domain-Model */
    fun toDomainModel(): PrintMedia {
        return PrintMedia(id = id, title = title, description = description)
    }
}
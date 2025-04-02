package com.zelkulon.printzone1.core.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zelkulon.printzone1.core.domain.model.Order

/**
 * DTO für Bestellung – zum Senden an / Empfangen vom Backend.
 * Kunden- und Adressreferenzen werden als IDs übertragen.
 */
data class OrderDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("printMediaId") val printMediaId: Int,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("customerId") val customerId: Int,
    @SerializedName("addressId") val addressId: Int
) {
    /** Konvertierung DTO -> Domain */
    fun toDomainModel(): Order {
        // Bei Bestellung vom Server sollte id nicht null sein
        return Order(
            id = id ?: 0,
            printMediaId = printMediaId,
            quantity = quantity,
            customerId = customerId,
            addressId = addressId
        )
    }

    companion object {
        /** Konvertierung Domain -> DTO (z.B. für POST Request) */
        fun fromDomain(order: Order): OrderDto {
            return OrderDto(
                printMediaId = order.printMediaId,
                quantity = order.quantity,
                customerId = order.customerId,
                addressId = order.addressId
            )
        }
    }
}
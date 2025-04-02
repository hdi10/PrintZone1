package com.zelkulon.printzone1.core.domain.model

/**
 * harun@zelkulon 2025
 * Adress - Lieferadresse als Domain Model
 */
data class Address(
    val id: Int,
    val customer_id: Int,
    val street: String,
    val postalcode: Int,
    val city: String
)

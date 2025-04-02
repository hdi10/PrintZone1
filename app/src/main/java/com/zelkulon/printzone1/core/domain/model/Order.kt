package com.zelkulon.printzone1.core.domain.model

/**
 * Order - Bestellungen als Domain-Model
 */

data class Order(
    val id: Int,
    val printMediaId: Int,
    val quantity: Int,
    val customerId: Int,
    val addressId: Int
)

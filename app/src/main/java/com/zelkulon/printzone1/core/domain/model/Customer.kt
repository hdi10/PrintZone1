package com.zelkulon.printzone1.core.domain.model

/**
 *  Customer - Kunden als Domain-Model
 *
 *  harun@zelkulon 2025
 *
 */
data class Customer(
    val id: Int,
    val name:String,
    val adress: String,
    val city: String,
    val postalcode: Int,
    val email: String,
    val telephonenumber: String
)

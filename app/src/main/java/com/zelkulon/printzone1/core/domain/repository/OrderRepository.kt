package com.zelkulon.printzone1.core.domain.repository

import com.zelkulon.printzone1.core.domain.model.Order

/**
 * Repository-Interface für Bestellungen
 */
interface OrderRepository {
    /**
     * Legt neue Bestellung an und gibt die erstellte Order zurück.
     * @throws Exception --> falls Bestellung nicht angelegt werden konnte
     */
    suspend fun createOrder(order: Order):Order
}
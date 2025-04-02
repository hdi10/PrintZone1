package com.zelkulon.printzone1.core.domain.usecase

import com.zelkulon.printzone1.core.domain.model.Order
import com.zelkulon.printzone1.core.domain.repository.OrderRepository


/**
 * Anwendungslogik: Eine neue Bestellung anlegen.
 */
class CreateOrderUseCase(private val orderRepository: OrderRepository) {

    /**
     * Führt den Use Case aus, um eine Bestellung anzulegen.
     * Validiert ggf. Eingaben und nutzt das Repository, um die Bestellung über das Backend zu erstellen.
     */
    suspend fun execute(order: Order): Order {
        // (Einfache Validierung als Beispiel)
        require(order.quantity > 0) { "Quantity must be greater than 0" }
        // Delegiere an das Repository, das den Netzwerkaufruf durchführt
        return orderRepository.createOrder(order)
    }
    }
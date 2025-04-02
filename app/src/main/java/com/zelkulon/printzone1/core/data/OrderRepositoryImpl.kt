package com.zelkulon.printzone1.core.data

import com.zelkulon.printzone1.core.data.local.dao.OrderDao
import com.zelkulon.printzone1.core.data.local.entity.OrderEntity
import com.zelkulon.printzone1.core.data.remote.ApiService
import com.zelkulon.printzone1.core.data.remote.dto.OrderDto
import com.zelkulon.printzone1.core.domain.model.Order
import com.zelkulon.printzone1.core.domain.repository.OrderRepository
import com.zelkulon.printzone1.port.exception.AppException


/**
 * Implementierung des OrderRepository.
 * Nutzt ApiService für Remote-Aufrufe und OrderDao für lokale Speicherung.
 */
class OrderRepositoryImpl(
    private val api: ApiService,
    private val orderDao: OrderDao
) : OrderRepository {

    override suspend fun createOrder(order: Order): Order {
        return try {
            // Bestellung über die API senden
            val responseDto = api.createOrder(OrderDto.fromDomain(order))
            val createdOrder = responseDto.toDomainModel()
            // Bestellung in lokale DB einfügen (mit serverId)
            val entity = OrderEntity(
                serverId = createdOrder.id,
                printMediaId = createdOrder.printMediaId,
                quantity = createdOrder.quantity,
                customerId = createdOrder.customerId,
                addressId = createdOrder.addressId
            )
            orderDao.insert(entity)
            // Erfolgreich erstellte Order (inkl. vom Server vergebener ID) zurückliefern
            createdOrder
        } catch (e: Exception) {
            // Im Fehlerfall (z.B. kein Netzwerk)
            // Optional: Bestellung lokal speichern und später synchronisieren
            throw AppException.NetworkException(e)
        }
    }
}

package com.zelkulon.printzone1.port.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zelkulon.printzone1.core.domain.model.Order
import com.zelkulon.printzone1.core.domain.usecase.CreateOrderUseCase
import com.zelkulon.printzone1.port.exception.AppException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** UI-State für den Bestellvorgang. */
sealed class OrderUiState {
    object Idle : OrderUiState()        // Warten auf Benutzereingabe
    object Submitting : OrderUiState()  // Bestellung wird gesendet
    data class Success(val order: Order) : OrderUiState()
    data class Error(val message: String) : OrderUiState()
}

class OrderViewModel(private val createOrderUseCase: CreateOrderUseCase) : ViewModel() {

    private val _orderState = MutableStateFlow<OrderUiState>(OrderUiState.Idle)
    val orderState: StateFlow<OrderUiState> = _orderState

    /**
     * Startet den Bestellvorgang mit den angegebenen Parametern.
     */
    fun submitOrder(printMediaId: Int, quantity: Int, customerId: Int, addressId: Int) {
        // Zustand auf "wird gesendet" setzen
        _orderState.value = OrderUiState.Submitting
        viewModelScope.launch {
            try {
                val newOrder = createOrderUseCase.execute(
                    Order(id = 0, printMediaId = printMediaId, quantity = quantity, customerId = customerId, addressId = addressId)
                )
                _orderState.value = OrderUiState.Success(newOrder)
            } catch (e: Exception) {
                val errorMsg = if (e is AppException) {
                    e.message ?: "Bestellung fehlgeschlagen"
                } else {
                    "Bestellung konnte nicht angelegt werden"
                }
                _orderState.value = OrderUiState.Error(errorMsg)
            }
        }
    }
}

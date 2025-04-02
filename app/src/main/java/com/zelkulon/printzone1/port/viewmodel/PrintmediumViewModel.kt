/*
package com.zelkulon.printzone1.port.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zelkulon.printzone1.core.domain.usecase.GetPrintMediaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PrintmediumViewModel : ViewModel() {

    private val _medien = MutableStateFlow<List<PrintMDto>>(emptyList())
    val medien: StateFlow<List<PrintmediumDto>> = _medien

    private val useCase = GetPrintMediaUseCase()

    fun ladeMedien() {
        viewModelScope.launch {
            try {
                val result = useCase.execute()
                _medien.value = result
            } catch (e: Exception) {
                // Error handling
            }
        }
    }
}*/

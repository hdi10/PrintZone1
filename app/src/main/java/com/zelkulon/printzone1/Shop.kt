package com.zelkulon.printzone1

import androidx.compose.runtime.Composable

data class Shop(
    val id: String,
    val name: String,
    val detailContent: @Composable () -> Unit
)
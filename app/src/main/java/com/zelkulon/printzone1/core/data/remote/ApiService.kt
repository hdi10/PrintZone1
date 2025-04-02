package com.zelkulon.printzone1.core.data.remote
import com.zelkulon.printzone1.core.data.remote.dto.OrderDto
import com.zelkulon.printzone1.core.data.remote.dto.PrintMediaDto
import retrofit2.http.*

/**
 * Retrofit-Service Interface für die REST-API des Backends.
 */
interface ApiService {
    @GET("/printmedia")
    suspend fun getPrintMediaList(): List<PrintMediaDto>

    @POST("/orders")
    suspend fun createOrder(@Body order: OrderDto): OrderDto
}
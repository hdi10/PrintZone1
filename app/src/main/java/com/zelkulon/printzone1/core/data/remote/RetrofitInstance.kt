package com.zelkulon.printzone1.core.data.remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:8080/" // ← für lokalen Server bei Android Emulator

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // <-- MUSS mit "/" enden
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
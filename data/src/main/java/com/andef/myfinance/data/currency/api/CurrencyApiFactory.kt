package com.andef.myfinance.data.currency.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyApiFactory {
    companion object {
        private const val BASE_URL = "https://cdn.jsdelivr.net/npm/@fawazahmed0/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private var instance: CurrencyApiService? = null

        fun getInstance(): CurrencyApiService {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                if (instance == null) {
                    instance = retrofit.create(CurrencyApiService::class.java)
                }
                return instance!!
            }
        }
    }
}
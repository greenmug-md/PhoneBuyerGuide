package com.greenmug.android.buyerguide.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Retrofit Api Client
 */
object ApiClient {

    fun getRetrofitClient(baseUrl: String): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

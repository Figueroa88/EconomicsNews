package com.alejandro.economicsnews.repository.client

import com.alejandro.economicsnews.repository.IWebService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient
{
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(IWebService::class.java)
    }
}
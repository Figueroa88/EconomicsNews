package com.alejandro.economicsnews.repository

import com.alejandro.economicsnews.data.model.response.EconomicNewsResponse
import retrofit2.http.GET

interface IWebService
{
    @GET("api")
    suspend fun getEconomicIndicatorsInfo(): EconomicNewsResponse
}
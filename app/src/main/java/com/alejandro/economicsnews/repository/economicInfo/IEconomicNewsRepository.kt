package com.alejandro.economicsnews.repository.economicInfo

import com.alejandro.economicsnews.data.model.IndicatorList

interface IEconomicNewsRepository
{
    suspend fun getIndicatorList(): IndicatorList
}
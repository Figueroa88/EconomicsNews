package com.alejandro.economicsnews.data.model

import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import org.junit.Assert.*
import org.junit.Test

class IndicatorListTest
{
    @Test
    fun givenIndicatorListDataWhenEntityListIsEmptyThenCreateIndicatorList()
    {
        var flag = true

        try
        {
            val indicatorList = mutableListOf<IndicatorEntity>()

            IndicatorList(indicatorList)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertTrue(flag)
    }

    @Test
    fun givenIndicatorListDataWhenEntityListIsNotEmptyThenCreateIndicatorListWithObjects()
    {
        var flag = true
        var indicatorObject: IndicatorList

        try
        {
            val indicatorList = mutableListOf<IndicatorEntity>()
            indicatorList.add(
                IndicatorEntity(
                    "uf",
                    "Unidad de Fomento",
                    "Pesos",
                    "12/08/2020",
                    0.0
                )
            )

            indicatorObject = IndicatorList(indicatorList)
        }
        catch (e: Exception)
        {
            flag = false

            val indicatorList = mutableListOf<IndicatorEntity>()

            indicatorObject = IndicatorList(indicatorList)
        }

        assertTrue(flag)
        assertNotEquals(0, indicatorObject.indicatorList.size)
    }
}
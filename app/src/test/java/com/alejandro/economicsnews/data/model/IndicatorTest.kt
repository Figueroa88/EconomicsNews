package com.alejandro.economicsnews.data.model

import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import org.junit.Assert.*
import org.junit.Test
import java.util.*


class IndicatorTest
{
    @Test
    fun givenIndicatorDataWhenAllDataIsValidThenCreateIndicator()
    {
        var flag = true

        try
        {
            Indicator("uf", "Unidad de Fomento", "Pesos", Date(), 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertTrue(flag)
    }

    @Test
    fun givenIndicatorDataWhenCodeIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            Indicator("", "Unidad de Fomento", "Pesos", Date(), 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenIndicatorDataWhenNameIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            Indicator("uf", "", "Pesos", Date(), 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenIndicatorDataWhenUnitIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            Indicator("uf", "Unidad de Fomento", "", Date(), 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenIndicatorDataWhenAllDataIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            Indicator("", "", "", Date(), 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenIndicatorDataForCreateEntityWhenAllDataIsValidThenCreateIndicatorEntity()
    {
        var flag = true
        var indicatorEntity: IndicatorEntity? = null

        try
        {
            val indicator = Indicator("uf", "Unidad de Fomento", "Pesos", Date(), 0.0)

            indicatorEntity = indicator.toIndicatorEntity()
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertTrue(flag)
        assertNotEquals(null, indicatorEntity)
    }
}
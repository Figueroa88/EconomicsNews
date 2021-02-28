package com.alejandro.economicsnews.data.model.entity

import org.junit.Assert.*
import org.junit.Test

class IndicatorEntityTest
{
    @Test
    fun givenIndicatorDataWhenAllDataIsValidThenCreateIndicatorEntity()
    {
        var flag = true

        try
        {
            IndicatorEntity("uf", "Unidad de Fomento", "Pesos", "12/08/2020", 0.0)
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
            IndicatorEntity("", "Unidad de Fomento", "Pesos", "12/08/2020", 0.0)
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
            IndicatorEntity("uf", "", "Pesos", "12/08/2020", 0.0)
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
            IndicatorEntity("uf", "Unidad de Fomento", "", "12//2020", 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenIndicatorDataWhenDateIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            IndicatorEntity("uf", "Unidad de Fomento", "Pesos", "", 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenIndicatorDataWhenAllDataAreInvalidThenReturnException()
    {
        var flag = true

        try
        {
            IndicatorEntity("", "", "", "", 0.0)
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }
}
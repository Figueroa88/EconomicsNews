package com.alejandro.economicsnews.data.model.response

import com.alejandro.economicsnews.data.model.Indicator
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class EconomicNewsResponseTest
{
    @Test
    fun givenEconomicDataWhenAllDataIsValidThenCreateEconomicNewResponse()
    {
        var flag = true

        try
        {
            val indicator = Indicator("uf", "unidad de Fomento", "Peso", Date(), 0.0)

            EconomicNewsResponse(
                "1.6.0",
                "mindicador.cl",
                Date(),
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator
            )
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertTrue(flag)
    }

    @Test
    fun givenEconomicDataWhenVersionIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            val indicator = Indicator("", "unidad de Fomento", "Peso", Date(), 0.0)

            EconomicNewsResponse(
                "",
                "mindicador.cl",
                Date(),
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator
            )
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenEconomicDataWhenAuthorIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            val indicator = Indicator("uf", "Unidad de Fomento", "Peso", Date(), 0.0)

            EconomicNewsResponse(
                "1.6.0",
                "",
                Date(),
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator
            )
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenEconomicDataWhenAllDataIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            val indicator = Indicator("uf", "unidad de Fomento", "Peso", Date(), 0.0)

            EconomicNewsResponse(
                "",
                "",
                Date(),
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator
            )
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenEconomicDataForConvertInListWhenAllDataIsValidThenConvertDataInList()
    {
        var flag = true
        var indicatorList = mutableListOf<IndicatorEntity>()

        try
        {
            val indicator = Indicator("uf", "unidad de Fomento", "Peso", Date(), 0.0)

            val response = EconomicNewsResponse(
                "1.6.0",
                "mindicador.cl",
                Date(),
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator,
                indicator
            )

            indicatorList = response.toIndicatorList()
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertTrue(flag)
        assertEquals(12, indicatorList.size)
    }
}
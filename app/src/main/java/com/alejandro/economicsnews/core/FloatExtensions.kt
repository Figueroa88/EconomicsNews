package com.alejandro.economicsnews.core

import java.lang.Exception
import java.text.NumberFormat
import java.util.*

fun Float.toNumberStringFormat(): String
{
    return try
    {
        return NumberFormat.getInstance(Locale.GERMANY).format(this)
    }
    catch (e: Exception)
    {
        this.toString()
    }
}

fun Double.toNumberStringFormat(): String
{
    return try
    {
        return NumberFormat.getInstance(Locale.GERMANY).format(this)
    }
    catch (e: Exception)
    {
        this.toString()
    }
}
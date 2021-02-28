package com.alejandro.economicsnews.core

import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringFormat(format: String): String
{
    return try
    {
        val formatDate = SimpleDateFormat(format, Locale.getDefault())

        formatDate.format(this)
    }
    catch (error: Exception)
    {
        ""
    }
}
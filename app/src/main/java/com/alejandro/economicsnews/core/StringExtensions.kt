package com.alejandro.economicsnews.core

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(dateFormat: String): Date
{
    return try
    {
        val formatDate = SimpleDateFormat(dateFormat, Locale.getDefault())

        formatDate.parse(this)!!
    }
    catch (error: Exception)
    {
        Date()
    }
}
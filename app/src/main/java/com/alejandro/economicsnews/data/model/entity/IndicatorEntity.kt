package com.alejandro.economicsnews.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alejandro.economicsnews.core.toDate
import com.alejandro.economicsnews.data.model.Indicator
import java.lang.IllegalArgumentException
import java.util.*

@Entity
data class IndicatorEntity(
    @PrimaryKey
    val code: String,
    val name: String,
    val unit: String,
    val date: String,
    val value: Double
)
{
    init
    {
        if (code == "" || name == "" || unit == "" || date == "")
        {
            throw IllegalArgumentException("Datos Inválidos de Indicador Entity")
        }
    }
}
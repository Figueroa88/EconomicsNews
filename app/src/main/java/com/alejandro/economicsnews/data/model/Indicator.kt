package com.alejandro.economicsnews.data.model

import com.alejandro.economicsnews.core.toStringFormat
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.google.gson.annotations.SerializedName
import java.lang.IllegalArgumentException
import java.util.*

data class Indicator(
    @SerializedName("codigo")
    val code: String,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("unidad_medida")
    val unit: String,
    @SerializedName("fecha")
    val date: Date,
    @SerializedName("valor")
    val value: Double
)
{
    init
    {
        if (code == "" || name == "" || unit == "")
        {
            throw IllegalArgumentException("Datos Inválidos de Indicador")
        }
    }
}

fun Indicator.toIndicatorEntity(): IndicatorEntity = IndicatorEntity(
    this.code,
    this.name,
    this.unit,
    this.date.toStringFormat("dd/MM/yyyy"),
    this.value
)

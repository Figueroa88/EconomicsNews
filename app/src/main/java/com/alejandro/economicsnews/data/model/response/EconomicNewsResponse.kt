package com.alejandro.economicsnews.data.model.response

import com.alejandro.economicsnews.data.model.Indicator
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.alejandro.economicsnews.data.model.toIndicatorEntity
import com.google.gson.annotations.SerializedName
import java.lang.IllegalArgumentException
import java.util.*

data class EconomicNewsResponse(
    val version: String,
    @SerializedName("autor")
    val author: String,
    @SerializedName("fecha")
    val date: Date,
    val uf: Indicator,
    val ivp: Indicator,
    @SerializedName("dolar")
    val dollar: Indicator,
    @SerializedName("dolar_intercambio")
    val dollarChange: Indicator,
    val euro: Indicator,
    val ipc: Indicator,
    val utm: Indicator,
    val imacec: Indicator,
    val tpm: Indicator,
    @SerializedName("libra_cobre")
    val poundCooper: Indicator,
    @SerializedName("tasa_desempleo")
    val unemploymentRate: Indicator,
    val bitcoin: Indicator
)
{
    init
    {
        if (version == "" || author == "" || date == null || uf == null || ivp == null || dollar == null ||
            dollarChange == null || euro == null || ipc == null || utm == null || imacec == null ||
            poundCooper == null || unemploymentRate == null || bitcoin == null || tpm == null
        )
        {
            throw IllegalArgumentException("Datos Inválidos")
        }
    }
}

fun EconomicNewsResponse.toIndicatorList(): MutableList<IndicatorEntity>
{
    val listReturn = mutableListOf<IndicatorEntity>()

    listReturn.add(this.uf.toIndicatorEntity())
    listReturn.add(this.ivp.toIndicatorEntity())
    listReturn.add(this.dollar.toIndicatorEntity())
    listReturn.add(this.dollarChange.toIndicatorEntity())
    listReturn.add(this.euro.toIndicatorEntity())
    listReturn.add(this.ipc.toIndicatorEntity())
    listReturn.add(this.utm.toIndicatorEntity())
    listReturn.add(this.imacec.toIndicatorEntity())
    listReturn.add(this.tpm.toIndicatorEntity())
    listReturn.add(this.poundCooper.toIndicatorEntity())
    listReturn.add(this.unemploymentRate.toIndicatorEntity())
    listReturn.add(this.bitcoin.toIndicatorEntity())

    return listReturn
}
package com.alejandro.economicsnews.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.IllegalArgumentException

@Entity
data class UserEntity(@PrimaryKey(autoGenerate = true) val id: Long, val name: String, val username: String, val access: String)
{
    init
    {
        if (name == "")
        {
            throw IllegalArgumentException("Datos Inválidos")
        }
        else if (username == "")
        {
            throw IllegalArgumentException("Nombre de usuario Inválido")
        }
        else if (access == "")
        {
            throw IllegalArgumentException("Clave de acceso Inválida")
        }
        else if (id < 0)
        {
            throw IllegalArgumentException("Id Inválido")
        }
    }
}

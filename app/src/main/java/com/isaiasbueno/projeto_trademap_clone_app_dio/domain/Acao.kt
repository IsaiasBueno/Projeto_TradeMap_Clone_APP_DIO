package com.isaiasbueno.projeto_trademap_clone_app_dio.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity
data class Acao(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val loginUsuario: String = "",
    val codigo: String = "",
    val horario: String = "",
    var valor: Double = 0.0
) : Parcelable {

    @Ignore
    var status: String? = null

    companion object {
        const val UP = "UP"
        const val DOWN = "DOWN"
    }
}

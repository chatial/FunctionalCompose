package id.ac.unpas.functionalcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class SetoranSampah(
    @PrimaryKey val id: String,
    val Tanggal_beli: String,
    val Nama_Pembeli: String,
    val Merek_sepatu: String,
    val Notelephone: String
)

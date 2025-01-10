package com.ikhdaamel.p10.ui.viewmodel

import com.ikhdaamel.p10.model.Mahasiswa

data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState()
)

data class FormErrorState(
    val nim : String? =null,
    val nama :String? =null,
    val jenisKelamin : String? =null,
    val alamat : String? =null,
    val kelas : String? =null,
    val angkatan : String? =null
){
    fun isValid(): Boolean{
        return nim == null && nama == null && jenisKelamin == null && alamat == null && kelas == null && angkatan == null
    }
}

//data class variabel yg menyimpan data input form
data class MahasiswaEvent(
    val nim : String ="",
    val nama : String ="",
    val jenisKelamin : String ="",
    val alamat : String ="",
    val kelas : String ="",
    val angkatan : String =""
)

//menyimpan input form ke entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)
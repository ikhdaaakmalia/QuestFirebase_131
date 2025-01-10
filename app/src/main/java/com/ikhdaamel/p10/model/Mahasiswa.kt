package com.ikhdaamel.p10.model

data class Mahasiswa (
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String
){
    //membuat konstruktor sbg awalan
    constructor() : this("","","","","","")
}
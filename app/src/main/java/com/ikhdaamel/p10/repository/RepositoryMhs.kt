package com.ikhdaamel.p10.repository

import com.ikhdaamel.p10.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

//sama dengan yg ada di P7-RoomDatabase RepositoryMhs
interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMahasiswa() : Flow<List<Mahasiswa>>
    fun getMhs (nim: String) : Flow<Mahasiswa>
    suspend fun deleteMhs (mahasiswa: Mahasiswa)
    suspend fun updateMhs (mahasiswa: Mahasiswa)
}
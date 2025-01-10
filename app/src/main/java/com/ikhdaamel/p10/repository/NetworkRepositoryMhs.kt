package com.ikhdaamel.p10.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ikhdaamel.p10.model.Mahasiswa
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

//sama dengan yg ada di P7-RoomDatabase LocalRepositoryMhs
class NetworkRepositoryMhs(
    private val firestore: FirebaseFirestore                        //panggil firebase
): RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        try{
            firestore.collection("Mahasiswa").add(mahasiswa).await()
        } catch (e: Exception) {
            throw Exception("Gagal menambahkan data mahasiswa: ${e.message} ")
        }
    }

    override fun getAllMahasiswa(): Flow<List<Mahasiswa>> = callbackFlow{         //callback digunakan agar dapat data secara realtime tanpa melakukan event (ex:on refresh)
        //val mhsCollection = membuka collection dari firedatabase
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)                      //bisa query jg tp udh ada fungsinya tinggal dipanggil
            .addSnapshotListener{                                                //yg membuat realtime(pasanganya callback)
                value, error ->
                if(value !=null){
                    val mhslist = value.documents.mapNotNull {
                        it.toObject(Mahasiswa::class.java)!!                       //convert dari dokumen firestore ke data class
                    }
                    trySend(mhslist)            //fungsi utk mengirim collection ke dataclass dari data yg ada didokumen dan udh di convert
                }
            }
        //menutup collection ke dataclass
        awaitClose{
            mhsCollection.remove()
        }
    }

    override fun getMhs(nim: String): Flow<Mahasiswa> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

}
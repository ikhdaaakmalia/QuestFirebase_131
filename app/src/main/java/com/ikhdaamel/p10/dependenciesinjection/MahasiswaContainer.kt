package com.ikhdaamel.p10.dependenciesinjection

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.ikhdaamel.p10.repository.NetworkRepositoryMhs
import com.ikhdaamel.p10.repository.RepositoryMhs


interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class MahasiswaContainer(private val contex: Context) : InterfaceContainerApp {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()      //tandanya harus melewati instance dulu
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkRepositoryMhs(firestore)                                             //memanggil networkrepo
    }
}
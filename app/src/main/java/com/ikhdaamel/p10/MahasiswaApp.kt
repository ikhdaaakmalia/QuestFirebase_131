package com.ikhdaamel.p10

import android.app.Application
import com.ikhdaamel.p10.dependenciesinjection.MahasiswaContainer

class MahasiswaApp : Application(){
    lateinit var containerApp: MahasiswaContainer
    override fun onCreate(){
        super.onCreate()                                        //membuat instace ContainerApp
        containerApp = MahasiswaContainer(this)         //instance adalah obj yg dibuat dari class
    }
}
package com.cekepek.ubayakuliner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cekepek.ubayakuliner.model.Kuliner
import com.cekepek.ubayakuliner.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailKulinerViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val kulinerLD= MutableLiveData<Kuliner>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id: Int){
        launch{
            val db = buildDb(getApplication())
            kulinerLD.postValue(db.KulinerDao().getDetail(id))
        }
    }
}
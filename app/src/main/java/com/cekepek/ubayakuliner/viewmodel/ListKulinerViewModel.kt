package com.cekepek.ubayakuliner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cekepek.ubayakuliner.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListKulinerViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val kulinerLD = MutableLiveData<List<Kuliner>>()
    val kulinerLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        kulinerLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            kulinerLD.postValue(db.KulinerDao().getData())
        }
    }
}
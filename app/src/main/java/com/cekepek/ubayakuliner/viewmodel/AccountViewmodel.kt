package com.cekepek.ubayakuliner.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccountViewmodel(application: Application): AndroidViewModel(application), CoroutineScope {

    val accountLD = MutableLiveData<Account>()
    val cekAccountLD = MutableLiveData<List<Account>>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.IO


    fun fetch(username: String, pass: String){
        launch {
            val db = buildDb(getApplication())
            accountLD.postValue(db.KulinerDao().login(username, pass))

        }
    }

    fun register(account: Account){
        launch {
            val db = buildDb(getApplication())
            db.KulinerDao().insertAll(account)
        }
    }

    fun cekAcc(username: String){
        launch {
            val db = buildDb(getApplication())
            cekAccountLD.postValue(db.KulinerDao().cekAcc(username))
        }
    }
}
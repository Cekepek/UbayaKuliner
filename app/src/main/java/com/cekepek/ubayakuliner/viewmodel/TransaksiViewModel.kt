package com.cekepek.ubayakuliner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.model.Kuliner
import com.cekepek.ubayakuliner.model.Transaksi
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TransaksiViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val kulinerLD = MutableLiveData<Kuliner>()
    val userLD = MutableLiveData<Account>()
    val transaksiLD = MutableLiveData<Transaksi>()
    val riwayatLD = MutableLiveData<List<Transaksi>>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id: Int){
        launch {
            val db = buildDb(getApplication())
            kulinerLD.postValue(db.KulinerDao().getDetail(id))
        }
    }

    fun getAccount(username: String){
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.KulinerDao().getAcc(username))
        }
    }

    fun updateBalance(balance: Int){
        launch {
            val db = buildDb(getApplication())
            db.KulinerDao().updateBalance(balance, Global.username)
        }
    }

    fun addTransaksi(transaksi: Transaksi){
        launch {
            val db = buildDb(getApplication())
            db.KulinerDao().insertTransaksi(transaksi)
        }
    }

    fun getTransaksi(id:String){
        launch {
            val db = buildDb(getApplication())
            transaksiLD.postValue(db.KulinerDao().getTransaksi(id))
        }
    }

    fun getRiwayatTransaksi(username: String){
        launch {
            val db = buildDb(getApplication())
            riwayatLD.postValue(db.KulinerDao().getRiwayatTransaksi(username))
        }
    }
}
package com.cekepek.ubayakuliner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cekepek.ubayakuliner.model.Kuliner
import com.cekepek.ubayakuliner.model.Review
import com.cekepek.ubayakuliner.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReviewViewmodel(application: Application): AndroidViewModel(application), CoroutineScope {

    val reviewLD = MutableLiveData<List<Review>>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getReviews(id:Int){
        launch {
            val db = buildDb(getApplication())
            reviewLD.postValue(db.KulinerDao().getReview(id))
        }
    }

    fun addReview(review: Review){
        launch {
            val db = buildDb(getApplication())
            db.KulinerDao().addReview(review)
        }
    }
}
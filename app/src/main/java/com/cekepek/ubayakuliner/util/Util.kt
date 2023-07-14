package com.cekepek.ubayakuliner.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.model.KulinerDatabase
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback

val DB_NAME = "kulinerdb"


fun buildDb(context: Context):KulinerDatabase {
    val db = Room.databaseBuilder(context.applicationContext,
        KulinerDatabase::class.java, DB_NAME)
        .build()

    return db
}

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback{
            override fun onSuccess() {
                progressBar.visibility= View.GONE
            }

            override fun onError(e: Exception?) {
            }
        })

}
package com.cekepek.ubayakuliner.util

import android.content.Context
import androidx.room.Room
import com.cekepek.ubayakuliner.model.KulinerDatabase

val DB_NAME = "kulinerdb"


fun buildDb(context: Context):KulinerDatabase {
    val db = Room.databaseBuilder(context.applicationContext,
        KulinerDatabase::class.java, DB_NAME)
        .build()

    return db
}
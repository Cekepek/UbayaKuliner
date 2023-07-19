package com.cekepek.ubayakuliner.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cekepek.ubayakuliner.util.*


@Database(entities = arrayOf(Account::class, Kuliner::class, Transaksi::class, Review::class), version =  9)
abstract class KulinerDatabase:RoomDatabase() {
    abstract fun KulinerDao(): KulinerDao

    companion object {
        @Volatile private var instance: KulinerDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                KulinerDatabase::class.java, "kulinerdb")
                .createFromAsset("kulinerdb.db")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
                    MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9)
                .build()

        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
package com.cekepek.ubayakuliner.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cekepek.ubayakuliner.util.MIGRATION_1_2
import com.cekepek.ubayakuliner.util.MIGRATION_2_3
import com.cekepek.ubayakuliner.util.MIGRATION_3_4
import com.cekepek.ubayakuliner.util.MIGRATION_4_5


@Database(entities = arrayOf(Account::class, Kuliner::class, Transaksi::class), version =  5)
abstract class KulinerDatabase:RoomDatabase() {
    abstract fun KulinerDao(): KulinerDao

    companion object {
        @Volatile private var instance: KulinerDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                KulinerDatabase::class.java, "kulinerdb")
                .createFromAsset("kulinerdb.db")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
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
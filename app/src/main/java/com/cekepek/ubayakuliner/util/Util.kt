package com.cekepek.ubayakuliner.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.Migration_1_2
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.model.KulinerDatabase
import com.cekepek.ubayakuliner.model.Transaksi
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


val DB_NAME = "kulinerdb"


fun buildDb(context: Context):KulinerDatabase {
    val db = Room.databaseBuilder(context.applicationContext,
        KulinerDatabase::class.java, DB_NAME)
        .createFromAsset("kulinerdb.db")
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
            MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9)
        .build()

    return db
}

val MIGRATION_1_2 = object:Migration (1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE accounts ADD COLUMN balance INTEGER DEFAULT 1000000 NOT NULL"
        )
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `kuliners` (`id` INTEGER NOT NULL, `nama` TEXT NOT NULL, `image` TEXT NOT NULL, `harga` INTEGER NOT NULL, `namaResto` TEXT NOT NULL, `description` TEXT NOT NULL, PRIMARY KEY(`id`))"
        )
    }
}
val MIGRATION_2_3 = object:Migration (2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE kuliners ADD COLUMN rating INTEGER DEFAULT 0 NOT NULL"
        )
    }
}
val MIGRATION_3_4 = object:Migration (3,4){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS kulinersTemp (`id` INTEGER NOT NULL, `nama` TEXT NOT NULL, `image` TEXT NOT NULL, `harga` INTEGER NOT NULL, `namaResto` TEXT NOT NULL, `description` TEXT NOT NULL, 'rating' REAL DEFAULT 0 NOT NULL,PRIMARY KEY(`id`))"
        )
        database.execSQL(
            "INSERT INTO kulinersTemp SELECT * FROM kuliners"
        )
        database.execSQL(
            "DROP TABLE kuliners"
        )
        database.execSQL(
            "ALTER TABLE kulinersTemp RENAME TO kuliners"
        )
    }
}

val MIGRATION_4_5 = object:Migration (4,5){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS transaksis (`id` TEXT NOT NULL, `idMakanan` INTEGER NOT NULL, `pembeli` TEXT NOT NULL, `total` INTEGER NOT NULL, `quantity` INTEGER NOT NULL, 'location' TEXT NOT NULL,PRIMARY KEY(`id`))"
        )
    }
}

val MIGRATION_5_6 = object:Migration (5,6){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS transaksisTemp (`id` TEXT NOT NULL, `namaMakanan` TEXT NOT NULL, `pembeli` TEXT NOT NULL, `total` INTEGER NOT NULL, `quantity` INTEGER NOT NULL, 'location' TEXT NOT NULL,PRIMARY KEY(`id`))"
        )
        database.execSQL(
            "DROP TABLE transaksis"
        )
        database.execSQL(
            "ALTER TABLE transaksisTemp RENAME TO transaksis"
        )
    }
}

val MIGRATION_6_7 = object:Migration (6,7){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS kulinersTemp (`id` INTEGER NOT NULL, `nama` TEXT NOT NULL, `image` TEXT, `harga` INTEGER NOT NULL, `namaResto` TEXT NOT NULL, `description` TEXT NOT NULL, 'rating' REAL DEFAULT 0 NOT NULL,PRIMARY KEY(`id`))"
        )
        database.execSQL(
            "INSERT INTO kulinersTemp SELECT * FROM kuliners"
        )
        database.execSQL(
            "DROP TABLE kuliners"
        )
        database.execSQL(
            "ALTER TABLE kulinersTemp RENAME TO kuliners"
        )
    }
}

val MIGRATION_7_8 = object:Migration (7,8){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS reviews (`id` INTEGER NOT NULL, `username` TEXT NOT NULL, `idMakanan` INTEGER NOT NULL, `komentar` TEXT NOT NULL, PRIMARY KEY(`id`))"
        )
    }
}

val MIGRATION_8_9 = object:Migration (8,9){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO reviews(username, idMakanan, komentar) VALUES('dummy', 0, 'dummy')"
        )
    }
}
fun ImageView.loadImage(url: String?, progressBar: ProgressBar?) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar?.visibility= View.GONE
            }

            override fun onError(e: Exception?) {
            }
        })
}

@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoURL(view:ImageView, url:String?, pb:ProgressBar){
    if(url != null){
        view.loadImage(url,pb)
    }
}

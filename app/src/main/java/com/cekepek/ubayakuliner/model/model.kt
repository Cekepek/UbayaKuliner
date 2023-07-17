package com.cekepek.ubayakuliner.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey
    var username:String,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name = "phone")
    var phone:String,
    @ColumnInfo(name = "location")
    var location:String
){
    @ColumnInfo(name = "balance")
    var balance:Int = 1000000
}

@Entity(tableName = "kuliners")
data class Kuliner(
    @ColumnInfo(name = "nama")
    var nama:String,
    @ColumnInfo(name = "image")
    var image:String?,
    @ColumnInfo(name = "harga")
    var harga:Int,
    @ColumnInfo(name = "namaResto")
    var namaResto:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "rating")
    var rating:Float
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity(tableName = "transaksis")
data class Transaksi(
    @PrimaryKey
    var id:String,
    @ColumnInfo(name= "namaMakanan")
    var namaMakanan:String,
    @ColumnInfo(name="pembeli")
    var pembeli:String,
    @ColumnInfo(name="total")
    var total:Int,
    @ColumnInfo(name= "quantity")
    var quantity:Int,
    @ColumnInfo(name="location")
    var location:String,
)

@Entity(tableName = "reviews")
data class Review(
    @ColumnInfo(name= "username")
    var username:String,
    @ColumnInfo(name="idMakanan")
    var idMakanan:Int,
    @ColumnInfo(name="komentar")
    var komentar:String,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
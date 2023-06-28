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
    var location:String,
)
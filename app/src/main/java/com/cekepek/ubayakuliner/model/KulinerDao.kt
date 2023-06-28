package com.cekepek.ubayakuliner.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KulinerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg account:Account)

    @Query("SELECT * FROM accounts WHERE username=:username AND password=:pass")
    fun login(username:String, pass:String): List<Account>
}
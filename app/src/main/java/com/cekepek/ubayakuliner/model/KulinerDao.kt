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
    fun login(username:String, pass:String): Account

    @Query("SELECT * FROM accounts WHERE username=:username")
    fun cekAcc(username: String): List<Account>

    @Query("SELECT * FROM kuliners")
    fun getData(): List<Kuliner>

    @Query("SELECT * FROM kuliners WHERE id=:id")
    fun getDetail(id: Int): Kuliner

    @Query("SELECT * FROM accounts WHERE username=:username")
    fun getAcc(username: String): Account

    @Query("UPDATE accounts SET balance=:balance WHERE username=:username")
    fun updateBalance(balance: Int, username: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaksi(vararg transaksi: Transaksi)

    @Query("SELECT * FROM transaksis WHERE id=:id")
    fun getTransaksi(id: String): Transaksi

    @Query("SELECT * FROM transaksis WHERE pembeli=:username")
    fun getRiwayatTransaksi(username: String): List<Transaksi>
}

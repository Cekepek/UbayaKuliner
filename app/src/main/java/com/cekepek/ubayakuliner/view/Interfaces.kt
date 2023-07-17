package com.cekepek.ubayakuliner.view

import android.view.View
import com.cekepek.ubayakuliner.model.Transaksi

interface FragmentDetailTransaksiLayoutInterface{
    fun onButtonKembali(v: View)
}

interface FragmentTransaksiLayoutInterface{
    fun onButtonBayar(v: View)
}

interface ActivityRegisterLayoutInterface{
    fun onButtonSignIn(v: View)
    fun onButtonRegister(v: View)
}

interface ActivityLoginLayoutInterface{
    fun onButtonSignUp(v: View)
    fun onButtonLogin(v: View)
}

interface RiwayatTransaksiLayoutInterface{
    fun onButtonDetail(v: View)
}

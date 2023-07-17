package com.cekepek.ubayakuliner.view

import android.view.View
import com.cekepek.ubayakuliner.model.Transaksi

interface FragmentDetailTransaksiLayoutInterface{
    fun onButtonKembali(v: View)
}

interface FragmentTransaksiLayoutInterface{
    fun onButtonBayar(v: View)
}
interface FragmentDetailListKulinerLayoutInterface{
    fun onCheckReview(v:View)
}

interface ActivityRegisterLayoutInterface{
    fun onButtonSignIn(v: View)
    fun onButtonRegister(v: View)
}

interface ActivityLoginLayoutInterface{
    fun onButtonSignUp(v: View)
    fun onButtonLogin(v: View)
    fun onButtonForget(v: View)
}

interface RiwayatTransaksiLayoutInterface{
    fun onButtonDetail(v: View)
}
interface ActivityUsernameCheckLayoutInterface{
    fun onButtonCheck(v: View)
}
interface  ActivityChangePasswordLayoutInterface{
    fun onButtonChangePwd(v: View)
}


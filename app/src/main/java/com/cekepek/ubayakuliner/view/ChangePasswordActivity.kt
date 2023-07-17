package com.cekepek.ubayakuliner.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.ActivityChangePasswordBinding
import com.cekepek.ubayakuliner.databinding.ActivityLoginBinding
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel

class ChangePasswordActivity : AppCompatActivity(), ActivityChangePasswordLayoutInterface {
    private lateinit var viewModel: AccountViewmodel
    private lateinit var dataBinding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding= DataBindingUtil.setContentView(this,R.layout.activity_change_password)
        viewModel = ViewModelProvider(this).get(AccountViewmodel::class.java)
        dataBinding.user = Account(Global.username,"","","")
        dataBinding.buttonListener = this



    }
    fun observeViewModel(){
        viewModel.cekAccountLD.observe(this, Observer{
            Log.e("cekdb", it.isEmpty().toString())
            if (it.isEmpty()){
                viewModel.updatePass(dataBinding.user!!.password, dataBinding.user!!.username)
                Toast.makeText(this, "Password berhasil diganti", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Password anda kurang unik", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onButtonChangePwd(v: View) {
        viewModel.cekPwd(dataBinding.user!!.password)
        observeViewModel()
    }
}
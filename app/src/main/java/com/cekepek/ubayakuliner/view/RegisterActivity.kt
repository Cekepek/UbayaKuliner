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
import com.cekepek.ubayakuliner.databinding.ActivityRegisterBinding
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel

class RegisterActivity : AppCompatActivity(), ActivityRegisterLayoutInterface {

    private lateinit var viewModel: AccountViewmodel
    private lateinit var dataBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding= DataBindingUtil.setContentView(this,R.layout.activity_register)


        viewModel = ViewModelProvider(this).get(AccountViewmodel::class.java)
        dataBinding.account = Account("","","","")
        dataBinding.buttonListener = this
    }

    fun observeViewModel(){
        viewModel.cekAccountLD.observe(this, Observer{
            Log.e("cekdb", it.isEmpty().toString())
            if (it.isEmpty()){
                viewModel.register(dataBinding.account!!)
                Toast.makeText(this,"Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Username sudah digunakan", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onButtonSignIn(v: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onButtonRegister(v: View) {
        viewModel.cekAcc(dataBinding.account!!.username)
        observeViewModel()
    }
}
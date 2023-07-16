package com.cekepek.ubayakuliner.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.ActivityLoginBinding
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel

class LoginActivity : AppCompatActivity(), ActivityLoginLayoutInterface {

    private lateinit var viewModel:AccountViewmodel
    private lateinit var dataBinding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(AccountViewmodel::class.java)
        Global.username = ""
        dataBinding.user = Account("","","","")
        dataBinding.buttonListener = this
    }


    fun observeViewModel(){
        viewModel.accountLD.observe(this, Observer{
            if (it == null){
                Toast.makeText(this, "Username/Password Salah", Toast.LENGTH_SHORT).show()
            }
            else{
                Global.username = it.username
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onButtonSignUp(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onButtonLogin(v: View) {
        viewModel.fetch(dataBinding.user!!.username, dataBinding.user!!.password)
        observeViewModel()
    }
}


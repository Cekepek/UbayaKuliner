package com.cekepek.ubayakuliner.view

import android.annotation.SuppressLint
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
import com.cekepek.ubayakuliner.databinding.ActivityLoginBinding
import com.cekepek.ubayakuliner.databinding.ActivityUsernameCheckBinding
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel
import com.google.android.material.textfield.TextInputLayout

class UsernameCheckActivity : AppCompatActivity(), ActivityUsernameCheckLayoutInterface {
    private lateinit var viewModel: AccountViewmodel
    private lateinit var dataBinding: ActivityUsernameCheckBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding= DataBindingUtil.setContentView(this,R.layout.activity_username_check)
        viewModel = ViewModelProvider(this).get(AccountViewmodel::class.java)
        Global.username = ""
        dataBinding.user = Account("","","","")
        dataBinding.buttonListener = this
    }

    fun observeViewModel(){
        viewModel.cekAccountLD.observe(this, Observer{
            Log.e("cekdb", it.isEmpty().toString())
            if (it.isEmpty()){
                Toast.makeText(this, "Username tidak terdaftar", Toast.LENGTH_SHORT).show()
            }
            else{
                Global.username = dataBinding.user!!.username
                Toast.makeText(this, "Username anda terdaftar", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ChangePasswordActivity::class.java)
                startActivity(intent)
            }
        })
    }
    override fun onButtonCheck(v: View) {
        viewModel.cekAcc(dataBinding.user!!.username)
        observeViewModel()
    }

}
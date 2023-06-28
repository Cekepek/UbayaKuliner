package com.cekepek.ubayakuliner.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel:AccountViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(AccountViewmodel::class.java)
        val username = findViewById<TextView>(R.id.txtUsername)
        val password = findViewById<TextView>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            viewModel.fetch(username.text.toString(), password.text.toString())
            observeViewModel()
        }
    }


    fun observeViewModel(){
        viewModel.accountLD.observe(this, Observer{
            Log.e("cekdb", it.isEmpty().toString())
            if (it.isEmpty()){
                Toast.makeText(this, "Username Salah", Toast.LENGTH_SHORT).show()
            }
            else{
                Global.login = true
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}


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
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: AccountViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this).get(AccountViewmodel::class.java)

        val btnRegis = findViewById<Button>(R.id.btnRegister)
        btnRegis.setOnClickListener {
            val username = findViewById<TextView>(R.id.txtRegisterUsername)
            viewModel.cekAcc(username.text.toString())
            observeViewModel()
        }

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        btnSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun observeViewModel(){
        viewModel.accountLD.observe(this, Observer{
            Log.e("cekdb", it.isEmpty().toString())
            if (it.isEmpty()){
                val username = findViewById<TextView>(R.id.txtRegisterUsername)
                val password = findViewById<TextView>(R.id.txtRegisterPassword)
                val phone = findViewById<TextView>(R.id.txtRegisterPhone)
                val location = findViewById<TextView>(R.id.txtRegisterLoc)
                val account = Account(username.text.toString(),password.text.toString(),phone.text.toString(),location.text.toString())
                viewModel.register(account)
                Toast.makeText(this,"Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Username sudah digunakan", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
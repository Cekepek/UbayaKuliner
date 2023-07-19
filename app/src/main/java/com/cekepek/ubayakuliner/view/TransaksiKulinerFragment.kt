package com.cekepek.ubayakuliner.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.FragmentDetailTransaksiBinding
import com.cekepek.ubayakuliner.databinding.FragmentTransaksiKulinerBinding
import com.cekepek.ubayakuliner.model.Transaksi
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.util.NotificationHelper
import com.cekepek.ubayakuliner.util.loadImage
import com.cekepek.ubayakuliner.viewmodel.TransaksiViewModel
import java.lang.Integer.parseInt
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TransaksiKulinerFragment : Fragment(), FragmentTransaksiLayoutInterface{

    private lateinit var viewModel:TransaksiViewModel
    private lateinit var dataBinding:FragmentTransaksiKulinerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentTransaksiKulinerBinding>(inflater,R.layout.fragment_transaksi_kuliner, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.transaksi = Transaksi("","",Global.username,10,1,"")
        dataBinding.buttonListener = this
        val id = TransaksiKulinerFragmentArgs.fromBundle(requireArguments()).idMakanan
        viewModel= ViewModelProvider(this).get(TransaksiViewModel::class.java)
        viewModel.fetch(id)
        viewModel.getAccount(Global.username)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kuliner = it
        })

        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

    override fun onButtonBayar(v: View) {
        val harga = dataBinding.transaksi!!.quantity*dataBinding.kuliner!!.harga
        if(dataBinding.user!!.balance<harga){
            Toast.makeText(activity, "Saldo anda Tidak Cukup ", Toast.LENGTH_SHORT).show()
        }
        else{
            dataBinding.transaksi!!.namaMakanan = dataBinding.kuliner!!.nama
            dataBinding.transaksi!!.location = dataBinding.user!!.location
            dataBinding.transaksi!!.total = harga
            viewModel.updateBalance(dataBinding.user!!.balance-harga)
            val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm")
            dataBinding.transaksi!!.id = LocalDateTime.now().format(formatter)
            viewModel.addTransaksi(dataBinding.transaksi!!)
            val action = TransaksiKulinerFragmentDirections.actionDetailTransaksi(dataBinding.transaksi!!.id)
            Navigation.findNavController(v).navigate(action)
        }
    }
}
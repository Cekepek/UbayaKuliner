package com.cekepek.ubayakuliner.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.FragmentDetailTransaksiBinding
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.util.NotificationHelper
import com.cekepek.ubayakuliner.util.loadImage
import com.cekepek.ubayakuliner.viewmodel.TransaksiViewModel

class DetailTransaksiFragment : Fragment(), FragmentDetailTransaksiLayoutInterface {

    private lateinit var viewModel:TransaksiViewModel
    private lateinit var dataBinding:FragmentDetailTransaksiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentDetailTransaksiBinding>(inflater,
            R.layout.fragment_detail_transaksi,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.transaksi
        dataBinding.buttonlistener = this
        NotificationHelper(view.context).createNotification("Transaksi Berhasil", "Makanan anda segera dikirim!")

        val id = DetailTransaksiFragmentArgs.fromBundle(requireArguments()).idPembelian
        viewModel= ViewModelProvider(this).get(TransaksiViewModel::class.java)
        viewModel.getTransaksi(id)

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.transaksiLD.observe(viewLifecycleOwner, Observer {
            dataBinding.transaksi = it
        })
    }

    override fun onButtonKembali(v: View) {
        val action = DetailTransaksiFragmentDirections.actionKembali()
        Navigation.findNavController(v).navigate(action)
    }
}
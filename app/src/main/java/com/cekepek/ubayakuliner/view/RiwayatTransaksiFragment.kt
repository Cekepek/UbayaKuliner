package com.cekepek.ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.TransaksiViewModel

class RiwayatTransaksiFragment : Fragment() {
    private lateinit var viewModel: TransaksiViewModel
    private val riwayatTransaksiAdapter = RiwayatTransaksiAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat_transaksi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
        viewModel.getRiwayatTransaksi(Global.username)
        var recView = view.findViewById<RecyclerView>(R.id.recViewTransaksi)
        recView.layoutManager =LinearLayoutManager(context)
        recView.adapter =riwayatTransaksiAdapter
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.riwayatLD.observe(viewLifecycleOwner, Observer {
            riwayatTransaksiAdapter.updateRiwayatTransaksi(it)
            val txtEmpty = view?.findViewById<TextView>(R.id.txtEmpty)
            val progressBar = view?.findViewById<ProgressBar>(R.id.progressBarRiwayatTransaksi)
            if(it.isEmpty()){
                txtEmpty?.visibility = View.VISIBLE
                progressBar?.visibility = View.VISIBLE
            }
            else{
                txtEmpty?.visibility = View.GONE
                progressBar?.visibility = View.GONE
            }
        })
    }
}
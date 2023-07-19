package com.cekepek.ubayakuliner.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.FragmentDetailListKulinerBinding
import com.cekepek.ubayakuliner.databinding.FragmentDetailTransaksiBinding
import com.cekepek.ubayakuliner.viewmodel.DetailKulinerViewModel


class DetailListKulinerFragment : Fragment(), FragmentDetailListKulinerLayoutInterface {

    private lateinit var viewModel: DetailKulinerViewModel
    private lateinit var dataBinding: FragmentDetailListKulinerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentDetailListKulinerBinding>(inflater,R.layout.fragment_detail_list_kuliner, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.kuliner
        dataBinding.buttonListener = this
        viewModel=ViewModelProvider(this).get(DetailKulinerViewModel::class.java)
        val id = DetailListKulinerFragmentArgs.fromBundle(requireArguments()).idMakanan
        viewModel.fetch(id)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer {
           dataBinding.kuliner = it
        })
    }

    override fun onCheckReview(v: View) {
        Log.e("tes", dataBinding.kuliner!!.id.toString())
        val action = DetailListKulinerFragmentDirections.actionReview(dataBinding.kuliner!!.id)
        Navigation.findNavController(v).navigate(action)
    }
}
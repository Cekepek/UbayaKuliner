package com.cekepek.ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.util.loadImage
import com.cekepek.ubayakuliner.view.DetailTransaksiFragmentArgs.Companion.fromBundle
import com.cekepek.ubayakuliner.view.TransaksiKulinerFragmentArgs.Companion.fromBundle
import com.cekepek.ubayakuliner.viewmodel.DetailKulinerViewModel


class DetailListKulinerFragment : Fragment() {

    private lateinit var viewModel: DetailKulinerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_list_kuliner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(DetailKulinerViewModel::class.java)

        val id = DetailListKulinerFragmentArgs.fromBundle(requireArguments()).idMakanan
        viewModel.fetch(id)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer {
            var imgViewKuliner=view?.findViewById<ImageView>(R.id.imgViewKuliner)
            var progressBarDetailKuliner=view?.findViewById<ProgressBar>(R.id.progressBarDetailKuliner)
            var txtNamaMakanan=view?.findViewById<TextView>(R.id.txtNamaKuliner)
            var txtHargaMakanan=view?.findViewById<TextView>(R.id.txtHargaMakanan)
            var txtLokasiUser=view?.findViewById<TextView>(R.id.txtLokasiUser)
            var ratingKulinerDetail=view?.findViewById<RatingBar>(R.id.ratingKulinerDetail)
            imgViewKuliner?.loadImage(it.image, progressBarDetailKuliner)
            txtNamaMakanan?.setText(it.nama)
            txtHargaMakanan?.setText(it.harga)
            txtLokasiUser?.setText(it.namaResto)
            ratingKulinerDetail?.rating=it.rating
        })
    }
}
package com.cekepek.ubayakuliner.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.model.Transaksi
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.util.loadImage
import com.cekepek.ubayakuliner.viewmodel.TransaksiViewModel
import java.lang.Integer.parseInt
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TransaksiKulinerFragment : Fragment() {

    private lateinit var viewModel:TransaksiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi_kuliner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = TransaksiKulinerFragmentArgs.fromBundle(requireArguments()).idMakanan
        viewModel= ViewModelProvider(this).get(TransaksiViewModel::class.java)
        viewModel.fetch(id)
        viewModel.getAccount(Global.username)
        observeViewModel()
        var btnBayar = view.findViewById<Button>(R.id.btnBayar)
        btnBayar.setOnClickListener {
            observeTransaksiViewModel(view)
        }
    }

    fun observeViewModel(){
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer {
            var imgKuliner = view?.findViewById<ImageView>(R.id.imgViewKuliner)
            var progressBar = view?.findViewById<ProgressBar>(R.id.progressBarTransaksi)
            imgKuliner?.loadImage(it.image,progressBar)
            val txtTotal = view?.findViewById<TextView>(R.id.txtTotalTransaksi)
            val txtJumlah = view?.findViewById<TextView>(R.id.txtJumlahMakanan)
            var harga = parseInt(txtJumlah?.text.toString()) * it.harga
            txtTotal?.text = harga.toString()
            txtJumlah?.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s.toString() == ""){
                        harga = 0
                    }
                    else{
                        harga = parseInt(s.toString()) * it.harga
                        txtTotal?.text = harga.toString()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            var txtNama = view?.findViewById<TextView>(R.id.txtNamaMakanan)
            txtNama?.setText(it.nama)
        })

        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            var txtTujuan = view?.findViewById<TextView>(R.id.txtTujuan)
            txtTujuan?.text = it.location
        })
    }
    fun observeTransaksiViewModel(v:View){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            var balance = it.balance
            val txtJumlah = view?.findViewById<TextView>(R.id.txtJumlahMakanan)
            var txtTujuan = view?.findViewById<TextView>(R.id.txtTujuan)
            var txtTotal = view?.findViewById<TextView>(R.id.txtTotalTransaksi)
            var harga = parseInt(txtTotal?.text.toString())
            if(balance<harga){
                Toast.makeText(activity, "Saldo anda Tidak Cukup ", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.updateBalance(balance-harga)
                val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm")
                val id = LocalDateTime.now().format(formatter)
                val idMakanan = TransaksiKulinerFragmentArgs.fromBundle(requireArguments()).idMakanan
                val jumlah =  parseInt(txtJumlah?.text.toString())
                val transaksi = Transaksi(id,idMakanan,Global.username, harga, jumlah, txtTujuan?.text.toString())
                viewModel.addTransaksi(transaksi)
                val action = TransaksiKulinerFragmentDirections.actionDetailTransaksi(id)
                Navigation.findNavController(v).navigate(action)
            }
        })
    }
}
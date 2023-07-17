package com.cekepek.ubayakuliner.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner.databinding.RiwayatTransaksiItemBinding
import com.cekepek.ubayakuliner.model.Kuliner
import com.cekepek.ubayakuliner.model.Transaksi

class RiwayatTransaksiAdapter(val transaksis:ArrayList<Transaksi>)
    :RecyclerView.Adapter<RiwayatTransaksiAdapter.TransaksiViewHolder>(), RiwayatTransaksiLayoutInterface {
    class TransaksiViewHolder(var view: RiwayatTransaksiItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = RiwayatTransaksiItemBinding.inflate(inflater, parent, false)
        return TransaksiViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransaksiViewHolder, position: Int) {
        holder.view.transaksi = transaksis[position]
        holder.view.buttonListener = this
    }

    override fun getItemCount(): Int {
        return transaksis.size
    }

    override fun onButtonDetail(v: View) {
        val action = RiwayatTransaksiFragmentDirections.actionDetailRiwayatTransaksi(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    fun updateRiwayatTransaksi(newListRiwayat: List<Transaksi>) {
        transaksis.clear()
        transaksis.addAll(newListRiwayat)
        notifyDataSetChanged()
    }
}
package com.cekepek.ubayakuliner.view

import android.media.Rating
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.ListKulinerItemBinding
import com.cekepek.ubayakuliner.databinding.ReviewItemBinding
import com.cekepek.ubayakuliner.model.Kuliner
import com.cekepek.ubayakuliner.util.loadImage
import java.lang.Integer.parseInt

class ListKulinerAdapter (val kulinerList:ArrayList<Kuliner>)
    :RecyclerView.Adapter<ListKulinerAdapter.ListKulinerViewHolder>(), ListKulinerLayoutInterface {

    class ListKulinerViewHolder (var view:ListKulinerItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKulinerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListKulinerItemBinding.inflate(inflater, parent, false)
        return ListKulinerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListKulinerViewHolder, position: Int) {
        holder.view.kuliner = kulinerList[position]
        holder.view.buttonListener = this
    }

    override fun getItemCount(): Int {
       return kulinerList.size
    }

    fun updateListKuliner(newListKuliner: List<Kuliner>) {
        kulinerList.clear()
        kulinerList.addAll(newListKuliner)
        notifyDataSetChanged()
    }

    override fun onButtonViewDetail(v: View) {
        val action=ListKulinerFragmentDirections.actionItemListKulinerToDetailListKulinerFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonBeli(v: View) {
        var action = ListKulinerFragmentDirections.actionTransaksi(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }


}
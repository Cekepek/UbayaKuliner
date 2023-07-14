package com.cekepek.ubayakuliner.view

import android.media.Rating
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.model.Kuliner
import com.cekepek.ubayakuliner.util.loadImage

class ListKulinerAdapter (val kulinerList:ArrayList<Kuliner>):RecyclerView.Adapter<ListKulinerAdapter.ListKulinerViewHolder>() {

    class ListKulinerViewHolder (var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKulinerViewHolder {
       val inflater=LayoutInflater.from(parent.context) 
        val view=inflater.inflate(R.layout.list_kuliner_item, parent, false)

        return ListKulinerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListKulinerViewHolder, position: Int) {
        var imageViewKuliner=holder.view.findViewById<ImageView>(R.id.imageViewKuliner)
        var ratingKuliner=holder.view.findViewById<RatingBar>(R.id.ratingKuliner)
        var textNamaKuliner=holder.view.findViewById<TextView>(R.id.txtNamaKuliner)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarItemKuliner)
        imageViewKuliner.loadImage(kulinerList[position].image, progressBar)
        ratingKuliner.rating= kulinerList[position].rating
        textNamaKuliner.text = kulinerList[position].nama
    }

    override fun getItemCount(): Int {
       return kulinerList.size
    }

    fun updateListKuliner(newListKuliner: List<Kuliner>) {
        kulinerList.clear()
        kulinerList.addAll(newListKuliner)
        notifyDataSetChanged()
    }


}
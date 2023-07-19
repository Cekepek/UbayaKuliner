package com.cekepek.ubayakuliner.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner.databinding.ReviewItemBinding
import com.cekepek.ubayakuliner.databinding.RiwayatTransaksiItemBinding
import com.cekepek.ubayakuliner.model.Review
import com.cekepek.ubayakuliner.model.Transaksi

class ReviewAdapter(val reviews:ArrayList<Review>)
    : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){
    class ReviewViewHolder(var view: ReviewItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ReviewItemBinding.inflate(inflater, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.view.review = reviews[position]
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    fun updateReview(newListReviews: List<Review>){
        reviews.clear()
        reviews.addAll(newListReviews)
        notifyDataSetChanged()
    }
}
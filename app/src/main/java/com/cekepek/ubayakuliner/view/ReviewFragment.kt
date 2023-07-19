package com.cekepek.ubayakuliner.view

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.model.Review
import com.cekepek.ubayakuliner.viewmodel.ListKulinerViewModel
import com.cekepek.ubayakuliner.viewmodel.ReviewViewmodel


class ReviewFragment : Fragment() {

    private lateinit var viewModel: ReviewViewmodel
    private val listReviewAdapter=ReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = ReviewFragmentArgs.fromBundle(requireArguments()).idMakanan
        viewModel= ViewModelProvider(this).get(ReviewViewmodel::class.java)
        viewModel.getReviews(id)
        observeViewModel()

    }

    fun observeViewModel(){
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            listReviewAdapter.updateReview(it)
            var txtErrorListReview = view?.findViewById<TextView>(R.id.txtErrorReview)
            var progressBar = view?.findViewById<ProgressBar>(R.id.progressBarReview)

            if(it.isEmpty()){
                txtErrorListReview?.visibility = View.VISIBLE
                progressBar?.visibility = View.VISIBLE
            }
            else{
                txtErrorListReview?.visibility = View.GONE
                progressBar?.visibility = View.GONE
            }
        })
    }
}
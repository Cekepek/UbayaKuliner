package com.cekepek.ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.FragmentAddReviewBinding
import com.cekepek.ubayakuliner.databinding.FragmentDetailListKulinerBinding
import com.cekepek.ubayakuliner.model.Review
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.DetailKulinerViewModel
import com.cekepek.ubayakuliner.viewmodel.ReviewViewmodel

class AddReviewFragment : Fragment(), FragmentAddReviewLayoutInterface {

    private lateinit var viewModel: ReviewViewmodel
    private lateinit var dataBinding: FragmentAddReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentAddReviewBinding>(inflater,R.layout.fragment_add_review, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = AddReviewFragmentArgs.fromBundle(requireArguments()).idMakanan
        dataBinding.review = Review(Global.username,id,"")
        dataBinding.buttonListener = this
        viewModel= ViewModelProvider(this).get(ReviewViewmodel::class.java)
    }
    override fun onButtonAddReview(v: View) {
        viewModel.addReview(dataBinding.review!!)
        Navigation.findNavController(v).popBackStack()
    }

}
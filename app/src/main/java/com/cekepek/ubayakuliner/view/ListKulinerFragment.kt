package com.cekepek.ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.viewmodel.ListKulinerViewModel


class ListKulinerFragment : Fragment() {

    private lateinit var viewModel:ListKulinerViewModel
    private val listKulinerAdapter=ListKulinerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_kuliner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(ListKulinerViewModel::class.java)
        viewModel.refresh()
        var recViewKuliner=view.findViewById<RecyclerView>(R.id.recViewKuliner)
        recViewKuliner.layoutManager=LinearLayoutManager(context)
        recViewKuliner.adapter=listKulinerAdapter

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer {
            listKulinerAdapter.updateListKuliner(it)
            var txtErrorListKuliner = view?.findViewById<TextView>(R.id.txtErrorListKuliner)
            var progressBar = view?.findViewById<ProgressBar>(R.id.progressBarListKuliner)

            if(it.isEmpty()) {
                txtErrorListKuliner?.visibility = View.VISIBLE
                progressBar?.visibility = View.VISIBLE
            } else {
                txtErrorListKuliner?.visibility = View.GONE
                progressBar?.visibility = View.GONE
            }
        })
    }
}
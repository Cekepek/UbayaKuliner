package com.cekepek.ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.FragmentChangePasswordBinding
import com.cekepek.ubayakuliner.databinding.FragmentProfileBinding
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel
import com.google.android.material.textfield.TextInputEditText

class ChangePasswordFragment : Fragment(),FragmentChangePasswordLayoutInterface {

    private lateinit var viewModel: AccountViewmodel
    private lateinit var dataBinding: FragmentChangePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentChangePasswordBinding>(inflater,R.layout.fragment_change_password, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.user = Account(Global.username, "","","")
        dataBinding.buttonListener = this
        viewModel= ViewModelProvider(this).get(AccountViewmodel::class.java)

        viewModel.getAccount(Global.username)

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.accountLD.observe(viewLifecycleOwner, Observer{
            dataBinding.user = it
        })
    }

    override fun onButtonSave(v: View) {
        viewModel.updatePass(dataBinding.user!!.password, dataBinding.user!!.username)
        Navigation.findNavController(v).popBackStack()
    }
}
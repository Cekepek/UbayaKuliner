package com.cekepek.ubayakuliner.view

import android.content.Intent
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
import com.cekepek.ubayakuliner.databinding.FragmentProfileBinding
import com.cekepek.ubayakuliner.databinding.FragmentTransaksiKulinerBinding
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel
import java.text.NumberFormat

class ProfileFragment : Fragment(), FragmentProfileLayoutInterface {

    private lateinit var viewModel: AccountViewmodel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.user = Account(Global.username, "", "", "")
        dataBinding.buttonListener = this

        viewModel=ViewModelProvider(this).get(AccountViewmodel::class.java)

        viewModel.getAccount(dataBinding.user!!.username)

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.accountLD.observe(viewLifecycleOwner, Observer{
            dataBinding.user = it
        })
    }

    override fun onButtonChangePassword(v: View) {
        val action= ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonEditProfile(v: View) {
        val action= ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onImgLogoutClick(v: View) {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}
package com.cekepek.ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel
import java.text.NumberFormat

class ProfileFragment : Fragment() {

    private lateinit var viewModel: AccountViewmodel
    var numberFormat= NumberFormat.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(AccountViewmodel::class.java)

        val username= Global.username
        val btnChangePasswordUser=view.findViewById<Button>(R.id.btnChangePasswordUser)
        val btnEditProfile=view.findViewById<Button>(R.id.btnEditProfile)

        viewModel.getAccount(username)

        observeViewModel()

        btnChangePasswordUser.setOnClickListener {
            val action= ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment(username)
            Navigation.findNavController(it).navigate(action)
        }

        btnEditProfile.setOnClickListener {
            val action= ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(username)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel(){
        viewModel.accountLD.observe(viewLifecycleOwner, Observer{
            val txtNamaUser=view?.findViewById<TextView>(R.id.txtNamaUserEdit)
            val txtNoTelpUser=view?.findViewById<TextView>(R.id.txtNoTelpUser)
            val txtRestaurant=view?.findViewById<TextView>(R.id.txtRestaurant)
            val txtSaldoWallet=view?.findViewById<TextView>(R.id.txtSaldoWalletEdit)

            txtNamaUser?.setText(it.username)
            txtNoTelpUser?.setText(it.phone)
            txtRestaurant?.setText(it.location)
            txtSaldoWallet?.setText("Rp${numberFormat.format(it.balance)}")
        })
    }
}
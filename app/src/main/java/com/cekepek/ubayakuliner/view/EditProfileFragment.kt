package com.cekepek.ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat

class EditProfileFragment : Fragment() {

    private lateinit var viewModel: AccountViewmodel
    var numberFormat= NumberFormat.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username=""

        viewModel= ViewModelProvider(this).get(AccountViewmodel::class.java)

        arguments?.let{
            val usernameSend=EditProfileFragmentArgs.fromBundle(requireArguments()).username
            username= usernameSend
        }

        viewModel.getAccount(username)

        observeViewModel()

        val txtEditNoTelp=view.findViewById<TextInputEditText>(R.id.txtEditNoTelp)
        val txtEditLocation=view.findViewById<TextInputEditText>(R.id.txtEditLocation)
        val btnUpdateProfile=view.findViewById<TextView>(R.id.btnUpdateProfile)

        btnUpdateProfile.setOnClickListener {
            viewModel.updateProfile(txtEditLocation.text.toString(), txtEditNoTelp.text.toString(), username)

            val action=EditProfileFragmentDirections.actionEditProfileFragmentToItemAkun()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel(){
        viewModel.accountLD.observe(viewLifecycleOwner, Observer{
            val txtNamaUser=view?.findViewById<TextView>(R.id.txtNamaUserEdit)
            val txtNoTelpUser=view?.findViewById<TextInputEditText>(R.id.txtEditNoTelp)
            val txtRestaurant=view?.findViewById<TextInputEditText>(R.id.txtEditLocation)
            val txtSaldoWallet=view?.findViewById<TextView>(R.id.txtSaldoWalletEdit)

            txtNamaUser?.setText(it.username)
            txtNoTelpUser?.setText(it.phone)
            txtRestaurant?.setText(it.location)
            txtSaldoWallet?.setText("Rp${numberFormat.format(it.balance)}")
        })
    }
}
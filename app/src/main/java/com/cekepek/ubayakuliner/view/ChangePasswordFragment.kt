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
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel
import com.google.android.material.textfield.TextInputEditText

class ChangePasswordFragment : Fragment() {

    private lateinit var viewModel: AccountViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(AccountViewmodel::class.java)

        var username=""

        arguments?.let{
            val usernameSend=EditProfileFragmentArgs.fromBundle(requireArguments()).username
            username= usernameSend
        }

        viewModel.getAccount(username)

        observeViewModel()

        val txtPasswordUpdate=view.findViewById<TextInputEditText>(R.id.txtPasswordUpdate)
        val btnUpdatePassword=view.findViewById<Button>(R.id.btnUpdatePassword)

        btnUpdatePassword.setOnClickListener {
            viewModel.updatePass(txtPasswordUpdate.text.toString(),  username)

            val action=ChangePasswordFragmentDirections.actionChangePasswordFragmentToItemAkun()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel(){
        viewModel.accountLD.observe(viewLifecycleOwner, Observer{
            val txtNamaUser=view?.findViewById<TextView>(R.id.txtUsernameUpdate)

            txtNamaUser?.setText(it.username)
        })
    }
}
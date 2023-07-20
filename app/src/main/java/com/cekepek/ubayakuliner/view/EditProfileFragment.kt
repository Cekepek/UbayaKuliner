package com.cekepek.ubayakuliner.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.databinding.FragmentChangePasswordBinding
import com.cekepek.ubayakuliner.databinding.FragmentEditProfileBinding
import com.cekepek.ubayakuliner.model.Account
import com.cekepek.ubayakuliner.util.Global
import com.cekepek.ubayakuliner.viewmodel.AccountViewmodel
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import kotlin.math.log

class EditProfileFragment : Fragment(),FragmentChangePasswordLayoutInterface {

    private lateinit var viewModel: AccountViewmodel
    private lateinit var dataBinding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditProfileBinding>(inflater,R.layout.fragment_edit_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.user = Account(Global.username, "","","")
        dataBinding.buttonListener = this

        viewModel= ViewModelProvider(this).get(AccountViewmodel::class.java)

        viewModel.getAccount(dataBinding.user!!.username)

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.accountLD.observe(viewLifecycleOwner, Observer{
            dataBinding.user = it
        })
    }
    fun cekAkun(username: String, noTelp:String, lokasi:String, usernameBaru:String){
        if(Global.username == dataBinding.user!!.username) {
            viewModel.updateProfile(noTelp, lokasi, username, usernameBaru)
            Toast.makeText(activity, "Profil berhasil diganti", Toast.LENGTH_SHORT).show()

            Global.username = usernameBaru
            view?.let { it1 -> Navigation.findNavController(it1).popBackStack() }
        }
        else{
            viewModel.cekAcc(usernameBaru)

            viewModel.cekAccountLD.observe(viewLifecycleOwner, Observer{
                if (it.isEmpty()){
                    viewModel.updateProfile(noTelp, lokasi, username, usernameBaru)
                    Toast.makeText(activity,"Profil berhasil diganti", Toast.LENGTH_SHORT).show()

                    Global.username=usernameBaru
                    view?.let { it1 -> Navigation.findNavController(it1).popBackStack()}
                }
                else{
                    Toast.makeText(activity,"Username telah terpakai", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onButtonSave(v: View) {
        cekAkun(Global.username, dataBinding.user!!.phone, dataBinding.user!!.location, dataBinding.user!!.username)
    }
}
package com.udacity.shoestore.screens

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        binding.signinButton.setOnClickListener {
            validateLogin()
        }

        binding.registerButton.setOnClickListener {
            validateLogin()
        }

        return binding.root
    }

    private fun validateLogin() {
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEdit.text.toString()).matches()) {
            Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_LONG).show()
        } else if(binding.passwordEdit.text.toString().length <= 5) {
            Toast.makeText(context, "Password is too short, more than 5 characters", Toast.LENGTH_LONG).show()
        } else {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
    }


}
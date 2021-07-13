package com.example.myernaehrungapplication.fragments.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.data.User.UserViewModel
import com.example.myernaehrungapplication.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class loginFragment : Fragment() {

    private lateinit var userViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPreferences = context?.getSharedPreferences(requireActivity().packageName, Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences?.getBoolean("IS_LOGGED_IN", false)
        if(isLoggedIn == true){
            findNavController().navigate(R.id.action_loginFragment_to_personalFragment)
        }

        val binding :FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.tvRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment);
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            if(etUsername.text.toString().equals("", ignoreCase = true)){
                Toast.makeText(activity, "Please enter UserName", Toast.LENGTH_SHORT).show()
            } else if(etPassword.text.toString().equals("", ignoreCase = true)){
                Toast.makeText(activity, "Please enter Password", Toast.LENGTH_SHORT).show()
            } else {
                userViewModel.checkValidLogin(etUsername.text.toString(), etPassword.text.toString()).observe(viewLifecycleOwner, Observer { result ->
                    //var isValid = userViewModel.checkValidLogin(etUsername.text.toString(), etPassword.text.toString())
                    if (result != null) {
                        val sharedPreferences = context?.getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        editor?.putBoolean("IS_LOGGED_IN", true)
                        editor?.putString("USER_EMAIL", result.email)
                        editor?.putString("USER_FIRST_NAME", result.firstname)
                        editor?.putString("USER_LAST_NAME", result.lastnamename)
                        editor?.apply()
                        Toast.makeText(activity, "Successfully Logged In!", Toast.LENGTH_LONG).show()
                        Log.i("Successful_Login", "Login was successful")
                        findNavController().navigate(R.id.action_loginFragment_to_personalFragment)
                    } else {
                        Toast.makeText(activity, "Invalid Login!", Toast.LENGTH_SHORT).show()
                        Log.i("Unsuccessful_Login", "Login was not successful")
                    }
                })
            }
        }
    }

}
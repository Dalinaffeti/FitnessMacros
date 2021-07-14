package com.example.myernaehrungapplication.fragments.registration

import android.os.Bundle
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
import com.example.myernaehrungapplication.data.User.User
import com.example.myernaehrungapplication.data.User.UserViewModel
import com.example.myernaehrungapplication.databinding.FragmentRegistrationBinding
import kotlinx.android.synthetic.main.fragment_registration.*


class registrationFragment : Fragment() {


    private lateinit var userViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding :FragmentRegistrationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_registration,
            container,
            false
        )
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       btnRegister.setOnClickListener {
           var firstname = etFirstname.text.toString()
           var lastname = etLastname.text.toString()
           var email = etEmail.text.toString()
           var password =etPassword.text.toString()
           var repassword = etConfirmPassword.text.toString()

           if(validateFields()){
               if(password != repassword){
                   Toast.makeText(requireContext(),"Password and Confrim Password is not matched",Toast.LENGTH_LONG).show()
               } else {
                   insertDataToDatabase( firstname,lastname,password,email)
               }
           }
        }
    }
   private fun insertDataToDatabase(firstname:String,lastnamename:String,password:String,email:String) {
       // Create User Object
       val user = User(0, email, password, firstname, lastnamename)
       userViewModel.addUser(user).observe(viewLifecycleOwner, { insertedId ->
           if(insertedId.toInt() != -1){
               Toast.makeText(requireContext(),"Successfully added !",Toast.LENGTH_LONG).show()
               findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
           } else {
               Toast.makeText(requireContext(),"User is not added in database",Toast.LENGTH_LONG).show()
           }
       })

    }

    private fun validateFields(): Boolean {
        return when {
            etFirstname.text.toString().equals("", ignoreCase = true) -> {
                Toast.makeText(activity, "Please enter First Name", Toast.LENGTH_SHORT).show()
                false
            }
            etLastname.text.toString().equals("", ignoreCase = true) -> {
                Toast.makeText(activity, "Please enter Last Name", Toast.LENGTH_SHORT).show()
                false
            }
            etEmail.text.toString().equals("", ignoreCase = true) -> {
                Toast.makeText(activity, "Please enter an Email", Toast.LENGTH_SHORT).show()
                false
            }
            etPassword.text.toString().equals("", ignoreCase = true) -> {
                Toast.makeText(activity, "Please enter Password", Toast.LENGTH_SHORT).show()
                false
            }
            etConfirmPassword.text.toString().equals("", ignoreCase = true) -> {
                Toast.makeText(activity, "Please enter Confirm Password", Toast.LENGTH_SHORT).show()
                false
            }
            !etConfirmPassword.text.toString().equals(etPassword.text.toString(), ignoreCase = false) -> {
                Toast.makeText(activity, "Confirm Password and Password mismatch", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

}
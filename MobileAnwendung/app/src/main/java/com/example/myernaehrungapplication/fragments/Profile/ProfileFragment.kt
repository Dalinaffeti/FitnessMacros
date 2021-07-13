package com.example.myernaehrungapplication.fragments.Profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding :FragmentProfileBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = context?.getSharedPreferences(requireActivity().packageName, Context.MODE_PRIVATE)
        val email = sharedPreferences?.getString("USER_EMAIL", "")
        val firstName = sharedPreferences?.getString("USER_FIRST_NAME", "")
        val lastName = sharedPreferences?.getString("USER_LAST_NAME", "")

        etFirstname.setText(firstName)
        etLastname.setText(lastName)
        etEmail.setText(email)

    }
}
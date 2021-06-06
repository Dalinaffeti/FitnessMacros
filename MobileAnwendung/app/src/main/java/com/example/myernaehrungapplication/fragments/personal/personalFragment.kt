package com.example.myernaehrungapplication.fragments.personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myernaehrungapplication.R
import kotlinx.android.synthetic.main.fragment_personal.view.*


class personalFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal, container, false)
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_personalFragment_to_goalFragment)
        }

        return view
    }


}
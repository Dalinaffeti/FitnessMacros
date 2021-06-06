package com.example.myernaehrungapplication.fragments.goal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myernaehrungapplication.R
import kotlinx.android.synthetic.main.fragment_goal.view.*
import kotlinx.android.synthetic.main.fragment_personal.view.*


class goalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_goal, container, false)
        view.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_goalFragment_to_nutritionFragment)
        }
        return view
    }


}
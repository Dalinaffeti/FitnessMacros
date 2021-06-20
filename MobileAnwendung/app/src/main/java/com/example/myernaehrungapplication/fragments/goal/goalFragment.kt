package com.example.myernaehrungapplication.fragments.goal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.databinding.FragmentGoalBinding
import kotlinx.android.synthetic.main.fragment_goal.view.*
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.fragment_personal.view.*


class goalFragment : Fragment() {
    private lateinit var viewModel: GoalViewModel
    private lateinit var viewModelFactory: GoalViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentGoalBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_goal,
            container,
            false
        )
        val args : goalFragmentArgs by navArgs()
      /*  viewModelFactory = GoalViewModelFactory(goalFragmentArgs)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GoalViewModel::class.java)*/

        binding.textView.text = args.weight.toString()

        // Inflate the layout for this fragment
        /*val view = inflater.inflate(R.layout.fragment_goal, container, false)
        view.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_goalFragment_to_nutritionFragment)
        }*/

        binding.floatingActionButton3.setOnClickListener {
            findNavController().navigate(R.id.action_goalFragment_to_personalFragment2);
        }
        return binding.root
    }


}
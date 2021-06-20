package com.example.myernaehrungapplication.fragments.personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.databinding.FragmentPersonalBinding
import kotlinx.android.synthetic.main.fragment_personal.view.*




class personalFragment : Fragment() {
    private lateinit var binding : FragmentPersonalBinding
    private lateinit var viewModel: PersonalViewModel


    /*private lateinit var binding: PersonalFragmentBinding
    private lateinit var viewModel: PersonalViewModel*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_personal,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(PersonalViewModel::class.java)
        viewModel.weight.observe(viewLifecycleOwner, Observer { newWeight ->
            binding.weight.text = newWeight.toString()
        })
        viewModel.word.observe(viewLifecycleOwner, Observer { newHeight ->
            binding.height.text = newHeight.toString()
        })



        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal, container, false)
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_personalFragment_to_goalFragment)
        }

        /*var kgs = view.findViewById<TextView>(R.id.weight);
        var kgsText = kgs.text.toString();
        var kgsNumber = kgsText.toInt();
        view.findViewById<AppCompatButton>(R.id.weighti).setOnClickListener {
            kgsNumber++;
            kgs.text = kgsNumber.toString();
        }
        view.findViewById<AppCompatButton>(R.id.weightd).setOnClickListener {
            kgsNumber--;
            kgs.text = kgsNumber.toString();
        }
        var height = view.findViewById<TextView>(R.id.height);
        var heightText = height.text.toString();
        var heightNumber = heightText.toInt();
        view.findViewById<AppCompatButton>(R.id.heighti).setOnClickListener {
            heightNumber++
            height.text= heightNumber.toString();
        }
        view.findViewById<AppCompatButton>(R.id.heightd).setOnClickListener {
            heightNumber--
            height.text= heightNumber.toString();
        }*/
        binding.weighti.setOnClickListener { onIncrease() }
        binding.weightd.setOnClickListener { onDecrease() }
        binding.floatingActionButton.setOnClickListener {PersonalFinished()}
        return binding.root
        /*return view*/
    }
    private fun onIncrease() {
        viewModel.onIncrease()

/*
        binding.weight.text= viewModel.weight.value.toString()
*/

    }
    private  fun onDecrease() {
        viewModel.onDecrease()

/*
      binding.weight.text= viewModel.weight.value.toString()
*/
    }
    private fun PersonalFinished(){
        val action = personalFragmentDirections.actionPersonalFragmentToGoalFragment()
        action.weight = viewModel.weight.value!!
        NavHostFragment.findNavController(this).navigate(action)
    }
}
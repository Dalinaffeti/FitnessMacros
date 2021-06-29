package com.example.myernaehrungapplication.fragments.personal

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.databinding.FragmentPersonalBinding
import kotlinx.android.synthetic.main.fragment_personal.view.*
import java.util.*


class personalFragment : Fragment() {
    var age: Int = 0
    private lateinit var binding : FragmentPersonalBinding
    private lateinit var viewModel: PersonalViewModel
    var sexe: String =""
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
        viewModel.height.observe(viewLifecycleOwner, Observer { newHeight ->
            binding.height.text = newHeight.toString()
        })
       /* viewModel.age.observe(viewLifecycleOwner, Observer { newAge ->
            binding.age.text = newAge.toString()
        })*/
        
        




        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal, container, false)
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_personalFragment_to_goalFragment)
        }
    binding.radioGroupmf.setOnCheckedChangeListener { group, checkedId ->
        if(checkedId == R.id.female) {
            sexe = "female"
            Toast.makeText(activity, "female selected",Toast.LENGTH_LONG).show()
        }
        else {
            sexe = "male"
            Toast.makeText(activity, "male selected",Toast.LENGTH_LONG).show()
        }
    }



        binding.selectDate.setOnClickListener {
            var c = Calendar.getInstance()
            var cDay = c.get(Calendar.DAY_OF_MONTH)
            var cMonth = c.get(Calendar.MONTH)
            var cYear = c.get(Calendar.YEAR)
            val calendarDialog = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener {
                    view, year, month, dayOfMonth ->
                cDay = dayOfMonth
                cMonth = month
                cYear = year
                textMessage("you selected the date: $cDay/${cMonth+1}/$cYear")
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                age = currentYear - cYear
                binding.age.visibility = View.VISIBLE
                binding.age.text = "you are $age years old"
                textMessage("you are $age years old")

            }, cYear,cMonth, cDay)

            calendarDialog.show()
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
        binding.weighti.setOnClickListener { onIncreaseW() }
        binding.weightd.setOnClickListener { onDecreaseW() }
        binding.heighti.setOnClickListener { onIncreaseH() }
        binding.heightd.setOnClickListener { onDecreaseH() }
        binding.floatingActionButton.setOnClickListener {PersonalFinished()}
        return binding.root
        /*return view*/
    }

    private fun textMessage(s: String) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
    }

    private fun onIncreaseW() {
        viewModel.onIncreaseWeight()

/*
        binding.weight.text= viewModel.weight.value.toString()
*/

    }
    private  fun onDecreaseW() {
        viewModel.onDecreaseHeight()

/*
      binding.weight.text= viewModel.weight.value.toString()
*/
    }


    private fun onIncreaseH(){
        viewModel.onIncreaseHeight()
    }

    private fun onDecreaseH(){
        viewModel.onDecreaseHeight();
    }



    private fun PersonalFinished(){
        val action = personalFragmentDirections.actionPersonalFragmentToGoalFragment()
        action.age = age
        action.sexe = sexe
        action.weight = viewModel.weight.value!!
        action.height = viewModel.height.value!!

        NavHostFragment.findNavController(this).navigate(action)
    }
}
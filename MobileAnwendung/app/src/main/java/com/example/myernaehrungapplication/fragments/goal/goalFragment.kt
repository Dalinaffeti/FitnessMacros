package com.example.myernaehrungapplication.fragments.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.data.Goal.GoalEntity
import com.example.myernaehrungapplication.data.Goal.GoalViewModel
import com.example.myernaehrungapplication.data.Personal.PersonalViewModel
import com.example.myernaehrungapplication.databinding.FragmentGoalBinding
import kotlinx.android.synthetic.main.fragment_goal.*


class goalFragment : Fragment() {
    private lateinit var viewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentGoalBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_goal,
            container,
            false
        )
        val args: goalFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this).get(GoalViewModel::class.java)
        /*  viewModelFactory = GoalViewModelFactory(goalFragmentArgs)

          viewModel = ViewModelProvider(this, viewModelFactory)
              .get(GoalViewModel::class.java)*/

        binding.weightFromPersonal.text = args.weight.toString()
        binding.heightFromPersonal.text = args.height.toString()
        binding.sexeFromPersonal.text = args.sexe.toString()
        binding.ageFromPersonal.text = args.age.toString()
        val activitysList = listOf(
            "Sedentray",
            "Low active",
            "Active",
            "Very active"
        )
        val femalePaList = listOf(1.0, 1.14, 1.27, 1.45)
        val malePaList = listOf(1.0, 1.12, 1.27, 1.54)

        val adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.support_simple_spinner_dropdown_item,
            activitysList
        )
        binding.spActivities.adapter = adapter
        var ageValue = args.age
        var weightCalculate = args.weight
        var heightCalculate = args.height / 100
        var calories = 0.0;
        var pa = 0.0
        var kgs = 0.0
        var gender = args.sexe.toString()
        var goal = ""

        binding.spActivities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val activ = parent?.getItemAtPosition(position).toString()
                //Toast.makeText(activity, "You selected ${activ}", Toast.LENGTH_LONG).show()
                if (gender == "male") {
                    when (activ) {
                        "Sendentary" -> pa = 1.0
                        "Low active" -> pa = 1.12
                        "Active" -> pa = 1.27
                        "Very active" -> pa = 1.54
                    }

                } else
                    if (gender == "female") {
                        when (activ) {
                            "Sendentary" -> pa = 1.0
                            "Low active" -> pa = 1.14
                            "Active" -> pa = 1.27
                            "Very active" -> pa = 1.45
                        }

                    }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.radioGroupGoal.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.lose) {
                goal = lose.text.toString()

            } else
                if (checkedId == R.id.maintain) {
                    goal = maintain.text.toString()
                } else
                    if (checkedId == R.id.gain) {
                        goal = gain.text.toString()
                    }
            //Toast.makeText(activity, goal + " selected", Toast.LENGTH_LONG).show()

        }
        binding.spinnerGoal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                kgs = parent?.getItemAtPosition(position).toString().toDouble()
                //Toast.makeText(activity, "You selected ${kgs} kgs", Toast.LENGTH_LONG).show()
                /*if (gender == "female")
                    when (goal) {

                        lose.text.toString() -> {

                            calories =
                                387 - 7.31 * ageValue + pa * (10.9 * (weightCalculate - kgs) + 660.7 * heightCalculate)

                        }
                        maintain.text.toString() -> {
                            calories =
                                387 - 7.31 * ageValue + pa * (10.9 * weightCalculate + 660.7 * heightCalculate)
                        }
                        gain.text.toString() -> {
                            calories =
                                387 - 7.31 * ageValue + pa * (10.9 * (weightCalculate + kgs) + 660.7 * heightCalculate)

                        }
                    }
                else
                    if (gender == "male") {
                        when (goal) {
                            lose.text.toString() -> {
                                calories =
                                    864 - 9.72 * ageValue + pa * (14.2 * (weightCalculate - kgs) + 503 * heightCalculate)
                            }
                            maintain.text.toString() -> {
                                calories =
                                    864 - 9.72 * ageValue + pa * (14.2 * weightCalculate + 503 * heightCalculate)
                            }
                            gain.text.toString() -> {
                                calories =
                                    864 - 9.72 * ageValue + pa * (14.2 * (weightCalculate + kgs) + 503 * heightCalculate)
                            }
                        }
                    }*/
                /*Toast.makeText(
                    activity, "Your calories ${calories}",
                    Toast.LENGTH_LONG
                ).show()*/
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        // Inflate the layout for this fragment
        /*val view = inflater.inflate(R.layout.fragment_goal, container, false)
        view.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_goalFragment_to_nutritionFragment)
        }*/

        binding.floatingActionButton3.setOnClickListener {
            findNavController().navigate(R.id.action_goalFragment_to_personalFragment2);
            /*activity?.onBackPressed()*/
        }
        binding.floatingActionButton2.setOnClickListener {
            when {
                goal.isEmpty() -> {
                    Toast.makeText(
                        activity, "Please Select Any Goal Type",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                pa == 0.0 -> {
                    Toast.makeText(
                        activity, "Please Select How Active You Are",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                kgs == 0.0 -> {
                    Toast.makeText(
                        activity, "Please Select How Much Your Work",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    when (gender) {
                        "male" -> {
                            calories = 864 - 9.72 * ageValue + pa * (14.2 * (weightCalculate - kgs) + 503 * heightCalculate)
                        }
                        else -> {
                            calories = 387 - 7.31 * ageValue + pa * (10.9 * (weightCalculate - kgs) + 660.7 * heightCalculate)
                        }
                    }

                    val goalObj = GoalEntity(0, goal, kgs.toFloat(), calories)
                    viewModel.addGoalInfo(goalObj).observe(viewLifecycleOwner, { insertedId ->
                        if(insertedId.toInt() != -1){
                            val action = goalFragmentDirections.actionGoalFragmentToNutritionFragment()
                            action.calories = calories.toString()
                            NavHostFragment.findNavController(this).navigate(action)
                        } else {
                            Toast.makeText(requireContext(),"Goal Info is not added in database",Toast.LENGTH_SHORT).show()
                        }
                    })

                }
            }

        }
        return binding.root
    }


}
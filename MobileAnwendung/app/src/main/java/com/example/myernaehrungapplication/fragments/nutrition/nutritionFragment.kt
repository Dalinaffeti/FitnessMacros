package com.example.myernaehrungapplication.fragments.nutrition

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.data.Nutrition.NutritionEntity
import com.example.myernaehrungapplication.data.Nutrition.NutritionViewModel
import com.example.myernaehrungapplication.data.Personal.PersonalViewModel
import com.example.myernaehrungapplication.databinding.FragmentGoalBinding
import com.example.myernaehrungapplication.databinding.FragmentNutritionBinding
import com.example.myernaehrungapplication.fragments.goal.goalFragmentArgs
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

import kotlinx.android.synthetic.main.fragment_nutrition.*
import kotlinx.android.synthetic.main.fragment_nutrition.view.*
import kotlinx.android.synthetic.main.fragment_personal.view.*
import java.security.KeyStore


class nutritionFragment : Fragment() {

    private lateinit var viewModel: NutritionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentNutritionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_nutrition,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(NutritionViewModel::class.java)
        val args : nutritionFragmentArgs by navArgs()
        val calories = args.calories.toFloat()
        // Inflate the layout for this fragment
        binding.setButton.setOnClickListener {
            setPieChart(binding, calories)
        }
        val view = inflater.inflate(R.layout.fragment_nutrition, container, false)
        binding.floatingActionButton4.setOnClickListener {
            findNavController().navigate(R.id.action_nutritionFragment_to_goalFragment)
        }
        binding.floatingActionButton2.setOnClickListener {

        }

        return binding.root;
    }

    fun setPieChart(binding: FragmentNutritionBinding, calories: Float){

        //x values
        val xvalues = ArrayList<String>()
        xvalues.add("Protein")
        xvalues.add("Carbohydrates")
        xvalues.add("Fat")

        //y values
/*
        val yvalues = ArrayList<Float>()
*/
        /*   yvalues.add(22.45f)
           yvalues.add(22.45f)
           yvalues.add()*/

        val pieChartEntry = ArrayList<Entry>()

        /* for((i, item) in yvalues.withIndex())
         {
             pieChartEntry.add(Entry(item, i))
         }*/
        var protein = binding.protein.text.toString().toFloat()
        var carbs = binding.carbs.text.toString().toFloat()

        var proteinPrcnt : Float  = ((protein*4)/calories.toFloat())*100
        var carbsPrcnt: Float = ((carbs*4)/calories.toFloat())*100
        var fatPrcnt : Float = 100-(proteinPrcnt+carbsPrcnt)

        val nutritionObj = NutritionEntity(0, proteinPrcnt, carbsPrcnt, fatPrcnt)
        viewModel.addNutritionInfo(nutritionObj).observe(viewLifecycleOwner, { insertedId ->
            if(insertedId.toInt() != -1) {
                binding.test.text = "carbs: "+ carbs + proteinPrcnt.toString() + "% ,"+carbsPrcnt.toString() + "% ," + fatPrcnt.toString()+ "%"
                pieChartEntry.add(Entry(proteinPrcnt, 0 ))
                pieChartEntry.add(Entry(carbsPrcnt, 1 ))
                pieChartEntry.add(Entry(fatPrcnt, 2 ))
                val colors = ArrayList<Int>()
                colors.add(Color.RED)
                colors.add(Color.YELLOW)
                colors.add(Color.GREEN)

                //fill the chart
                val pieDataSet  = PieDataSet(pieChartEntry,"Macronutrients")
                pieDataSet.colors = colors
                pieDataSet.sliceSpace = 3f
                val data = PieData(xvalues, pieDataSet)

                binding.pieChart.data= data
                binding.pieChart.holeRadius = 5f
                //binding.pieChart.setDescription("Macronutrients divided after user wish")
                binding.pieChart.animateY(3000)
            } else {
                Toast.makeText(requireContext(),"Nutrition Info is not added in database", Toast.LENGTH_SHORT).show()
            }
        })
    }




}
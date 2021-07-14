package com.example.myernaehrungapplication.fragments.tracking

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myernaehrungapplication.R
import com.example.myernaehrungapplication.adapters.WeightHistoryAdapter
import com.example.myernaehrungapplication.data.Personal.PersonalEntity
import com.example.myernaehrungapplication.data.Personal.PersonalViewModel
import com.example.myernaehrungapplication.databinding.FragmentTrackingBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_tracking.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TrackingFragment : Fragment() {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val outputFormat = SimpleDateFormat("dd MMM HH:mm")

    private lateinit var viewModel: PersonalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTrackingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tracking,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(PersonalViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchAllPersonalInfo().observe(viewLifecycleOwner, {
            val historyList: List<PersonalEntity> = it
            if (historyList.isEmpty()) {
                tvNoHistory.visibility = View.VISIBLE
                rvWeightHistory.visibility = View.GONE
                linechart.visibility = View.GONE
            } else {
                tvNoHistory.visibility = View.GONE
                rvWeightHistory.visibility = View.VISIBLE
                linechart.visibility = View.VISIBLE
                linechart.setPinchZoom(true)
                rvWeightHistory.adapter = WeightHistoryAdapter(historyList)
                rvWeightHistory.layoutManager = LinearLayoutManager(requireContext())
                rvWeightHistory.setHasFixedSize(true)

                val xvalue = ArrayList<String>()
                val lineetry = ArrayList<Entry>();
                for (i in 0 until it.size) {
                    val dateTime: Date = inputFormat.parse(it[i].createdAt)
                    val formattedDateTime = outputFormat.format(dateTime)
                    xvalue.add(formattedDateTime)
                    lineetry.add(Entry(it[i].weight.toFloat(), i))
                }


                val linedataset = LineDataSet(lineetry, "First")
                linedataset.color = resources.getColor(R.color.blue)
                val data = LineData(xvalue, linedataset)
                linechart.data = data
                linechart.setBackgroundColor(resources.getColor(R.color.white))
                linechart.setViewPortOffsets(0f, 0f, 0f, 0f)
                linechart.animateXY(3000, 3000)
                // get the legend (only possible after setting data)
                val l = linechart.legend
                l.isEnabled = false

                val xAxis = linechart.xAxis
                xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
                xAxis.textSize = 10f
                xAxis.textColor = Color.YELLOW
                xAxis.setDrawAxisLine(false)
                xAxis.setDrawGridLines(true)
                xAxis.textColor = Color.rgb(255, 192, 56)

                val leftAxis = linechart.getAxisLeft()
                leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
                leftAxis.setTextColor(Color.GREEN)
                leftAxis.setDrawGridLines(true)
                leftAxis.setGranularityEnabled(true)
                leftAxis.setYOffset(-9f);
                leftAxis.setTextColor(Color.rgb(255, 192, 56));

                val rightAxis = linechart.getAxisRight();
                rightAxis.setEnabled(false)

                linechart.invalidate()
            }
        })


    }













}
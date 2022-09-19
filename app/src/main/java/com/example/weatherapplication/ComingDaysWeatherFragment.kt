package com.example.weatherapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.weatherapplication.databinding.ComingDaysWeatherFragmentViewBinding
import com.example.weatherapplication.model.WeatherDetailsModel
import java.io.Serializable

class ComingDaysWeatherFragment : Fragment() {

    private lateinit var binding: ComingDaysWeatherFragmentViewBinding
    private var listData: MutableList<*>? = null

    companion object {
        fun getInstance(listData: MutableList<WeatherDetailsModel>?): ComingDaysWeatherFragment {
            val comingDaysWeatherFragment = ComingDaysWeatherFragment()
            Bundle().apply {
                putSerializable("WeatherDetails", listData as Serializable)
                comingDaysWeatherFragment.arguments = this
            }
            return comingDaysWeatherFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ComingDaysWeatherFragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            listData = getSerializable("WeatherDetails") as MutableList<*>?
        }

        loadUIComponents()
    }

    private fun loadUIComponents() {
        listData?.run {
            val divider = DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
            divider.setDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.recycler_decoration_divider
                )!!
            )
            val weatherAdapter = WeatherReportAdapter(requireActivity(), this)
            binding.weatherListField.apply {
                setHasFixedSize(false)
                addItemDecoration(divider)
                adapter = weatherAdapter
            }
        }
    }
}
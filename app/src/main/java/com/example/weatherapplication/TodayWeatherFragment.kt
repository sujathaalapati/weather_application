package com.example.weatherapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapplication.databinding.FragmentTodayWeatherDetailsBinding
import com.example.weatherapplication.model.WeatherDetailsModel
import com.example.weatherapplication.utils.DateUtilities
import java.io.Serializable
import java.util.*

class TodayWeatherFragment : Fragment() {

    private lateinit var binding: FragmentTodayWeatherDetailsBinding
    private var weatherDetailsModel: WeatherDetailsModel? = null

    companion object {
        fun getInstance(weatherDetailsModel: WeatherDetailsModel?): TodayWeatherFragment {
            val todayWeatherFragment = TodayWeatherFragment()
            Bundle().apply {
                putSerializable("WeatherDetails", weatherDetailsModel as Serializable)
                todayWeatherFragment.arguments = this
            }

            return todayWeatherFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTodayWeatherDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            weatherDetailsModel = getSerializable("WeatherDetails") as WeatherDetailsModel?
        }

        updateUIComponents()
    }

    private fun updateUIComponents() {
        val temperature = weatherDetailsModel?.temp
        if (temperature != 0.0) {
            binding.tvTodayDateField.text =
                DateUtilities.getDateStringFromCalendar2(Calendar.getInstance())
            val temperatureTxt = "${temperature}°C"
            binding.tvTemperatureField.text = temperatureTxt

            var feelsLikeText = getString(R.string.feels_like)
            feelsLikeText = feelsLikeText.replace("#$", "${temperature}°")
            binding.tvFeelsLikeField.text = feelsLikeText
        }
    }
}
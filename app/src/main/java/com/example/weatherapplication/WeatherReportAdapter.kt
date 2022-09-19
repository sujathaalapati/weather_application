package com.example.weatherapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.WeathersDetailsListItemsBinding
import com.example.weatherapplication.model.WeatherDetailsModel
import com.example.weatherapplication.utils.DateUtilities

class WeatherReportAdapter(
    private val context: Context,
    private val listData: MutableList<*>?
) :
    RecyclerView.Adapter<WeatherReportAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WeathersDetailsListItemsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataObject = listData?.get(position)
        if (dataObject is WeatherDetailsModel) {
            holder.bind(dataObject)
        }
    }

    override fun getItemCount(): Int {
        return listData?.size ?: 0
    }

    inner class ViewHolder(private val binding: WeathersDetailsListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherReportDetails: WeatherDetailsModel) {
            binding.tvTitleField.text = DateUtilities.getDateStringFromCalendar(weatherReportDetails.calendar)
            binding.tvWeatherDescField.text = weatherReportDetails.weather?.description
            val minTemp = "${weatherReportDetails.lowTemp}°C"
            binding.tvMinTempField.text = minTemp
            val maxTemp = "${weatherReportDetails.maxTemp}°C"
            binding.tvMaxTempField.text = maxTemp

            val code = weatherReportDetails.weather?.icon
            val drawable = ContextCompat.getDrawable(context, context.resources.getIdentifier(code, "drawable", context.packageName))
            binding.ivImageField.setImageDrawable(drawable)
        }
    }
}
package com.example.weatherapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherDetailsResponseModel : Serializable {

    @SerializedName("data")
    @Expose
    var data: MutableList<WeatherDetailsModel>? = null

    @SerializedName("city_name")
    @Expose
    var cityName: String? = null

    @SerializedName("lon")
    @Expose
    var lon: Double = 0.0

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null

    @SerializedName("lat")
    @Expose
    var lat: Double = 0.0

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null

    @SerializedName("state_code")
    @Expose
    var stateCode: String? = null
}
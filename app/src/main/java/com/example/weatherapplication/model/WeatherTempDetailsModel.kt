package com.example.weatherapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherTempDetailsModel : Serializable {

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null
}
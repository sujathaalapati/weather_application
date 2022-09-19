package com.example.weatherapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class WeatherDetailsModel : Serializable {

    @SerializedName("moonrise_ts")
    @Expose
    var moonriseTs: Double = 0.0

    @SerializedName("wind_cdir")
    @Expose
    var windCdir: String? = null

    @SerializedName("rh")
    @Expose
    var rh: Double = 0.0

    @SerializedName("pres")
    @Expose
    var pres: Double = 0.0

    @SerializedName("high_temp")
    @Expose
    var highTemp: Double = 0.0

    @SerializedName("sunset_ts")
    @Expose
    var sunsetTs: Double = 0.0

    @SerializedName("ozone")
    @Expose
    var ozone: Double = 0.0

    @SerializedName("moon_phase")
    @Expose
    var moonPhase: Double = 0.0

    @SerializedName("wind_gust_spd")
    @Expose
    var windGustSpd: Double = 0.0

    @SerializedName("snow_depth")
    @Expose
    var snowDepth: Double = 0.0

    @SerializedName("clouds")
    @Expose
    var clouds: Double = 0.0

    @SerializedName("ts")
    @Expose
    var ts: Double = 0.0

    @SerializedName("sunrise_ts")
    @Expose
    var sunriseTs: Double = 0.0

    @SerializedName("app_min_temp")
    @Expose
    var appMinTemp: Double = 0.0

    @SerializedName("wind_spd")
    @Expose
    var windSpd: Double = 0.0

    @SerializedName("pop")
    @Expose
    var pop: Double = 0.0

    @SerializedName("wind_cdir_full")
    @Expose
    var windCdirFull: String? = null

    @SerializedName("moon_phase_lunation")
    @Expose
    var moonPhaseLunation: Double = 0.0

    @SerializedName("slp")
    @Expose
    var slp: Double = 0.0

    @SerializedName("app_max_temp")
    @Expose
    var appMaxTemp: Double = 0.0

    @SerializedName("valid_date")
    @Expose
    var validDate: String? = null

    @SerializedName("vis")
    @Expose
    var vis: Double = 0.0

    @SerializedName("snow")
    @Expose
    var snow: Double = 0.0

    @SerializedName("dewpt")
    @Expose
    var dewpt: Double = 0.0

    @SerializedName("uv")
    @Expose
    var uv: Double = 0.0

    @SerializedName("weather")
    @Expose
    var weather: WeatherTempDetailsModel? = null

    @SerializedName("wind_dir")
    @Expose
    var windDir: Double = 0.0

    @SerializedName("max_dhi")
    @Expose
    var maxDhi: Any? = null

    @SerializedName("clouds_hi")
    @Expose
    var cloudsHi: Double = 0.0

    @SerializedName("precip")
    @Expose
    var precip: Double = 0.0

    @SerializedName("low_temp")
    @Expose
    var lowTemp: Double = 0.0

    @SerializedName("max_temp")
    @Expose
    var maxTemp: Double = 0.0

    @SerializedName("moonset_ts")
    @Expose
    var moonsetTs: Double = 0.0

    @SerializedName("datetime")
    @Expose
    var datetime: String? = null

    @SerializedName("temp")
    @Expose
    var temp: Double = 0.0

    @SerializedName("min_temp")
    @Expose
    var minTemp: Double = 0.0

    @SerializedName("clouds_mid")
    @Expose
    var cloudsMid: Double = 0.0

    @SerializedName("clouds_low")
    @Expose
    var cloudsLow: Double = 0.0

    var calendar : Calendar = Calendar.getInstance()
}
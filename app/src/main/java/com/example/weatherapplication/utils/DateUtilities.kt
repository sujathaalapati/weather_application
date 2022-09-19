package com.example.weatherapplication.utils

import android.text.TextUtils
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateUtilities {

    fun getCalendarFromDateString(dateValue: String?): Calendar {
        val locale = Locale.getDefault()
        if (TextUtils.isEmpty(dateValue)) {
            return Calendar.getInstance(locale)
        }
        try {
            val formatter = SimpleDateFormat(CALENDAR_DATE_FORMAT_ONE, locale) // MM/dd/yyyy
            val date = formatter.parse(dateValue!!)
            val cal = Calendar.getInstance(locale)
            cal.time = date!!
            return cal
        } catch (ex: Exception) {
            ex.message?.let { Log.d("getCalendarFromDateString", it) }
        }
        return Calendar.getInstance(locale)
    }

    fun getDateStringFromCalendar(lCalendar: Calendar): String {
        val locale = Locale.getDefault()
        try {
            val formatter = SimpleDateFormat(CALENDAR_DATE_FORMAT_TWO, locale) //EEEE, dd MMM
            return formatter.format(lCalendar.time)
        } catch (ex: Exception) {
            ex.message?.let { Log.d("getDateStringFromCalendar", it) }
        }

        return getDateStringFromCalendar(Calendar.getInstance(locale))
    }

    fun getDateStringFromCalendar2(lCalendar: Calendar) : String {
        val locale = Locale.getDefault()
        try {
            val formatter = SimpleDateFormat(CALENDAR_DATE_FORMAT_THREE, locale) //EEEE, dd MMM
            return formatter.format(lCalendar.time)
        } catch (ex: Exception) {
            ex.message?.let { Log.d("getDateStringFromCalendar2", it) }
        }

        return getDateStringFromCalendar(Calendar.getInstance(locale))
    }

    const val CALENDAR_DATE_FORMAT_ONE = "yyyy-MM-dd"
    const val CALENDAR_DATE_FORMAT_TWO = "EEEE, dd MMM"
    const val CALENDAR_DATE_FORMAT_THREE = "dd MMMM, HH:mm"
}
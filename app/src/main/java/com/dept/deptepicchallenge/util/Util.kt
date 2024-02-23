package com.dept.deptepicchallenge.util

import java.text.SimpleDateFormat
import java.util.Locale

object Util {
    fun getDayName(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)

        if (date != null) {
            val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            return dayOfWeekFormat.format(date)
        }

        return ""
    }

    fun getFormatDate(date: String) = date.replace(Constants.MIDDLEDASH, Constants.DASH)

}
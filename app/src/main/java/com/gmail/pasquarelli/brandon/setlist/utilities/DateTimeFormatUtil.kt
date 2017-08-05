package com.gmail.pasquarelli.brandon.setlist.utilities

import android.content.Context
import android.text.format.DateFormat
import com.gmail.pasquarelli.brandon.setlist.R
import java.util.*


class DateTimeFormatUtil {

    private val FORMAT_TYPE_OTHER = 0
    private val FORMAT_TYPE_TODAY = 2
    private val FORMAT_TYPE_TOMORROW = 3
    private val FORMAT_TYPE_YESTERDAY = 1

    /**
     * Use to convert a date in milliseconds to a String based on the device's locale.
     * @param context Application context; used for retrieving the default locale.
     * *
     * @param dateInMillis The date to be converted from milliseconds
     * *
     * @return A string representing the date that was passed in formatted for the user's locale.
     */
    fun dateFormatedForLocale(context: Context?, dateInMillis: Long): String? {
        val timeFormat = DateFormat.getTimeFormat(context)

        val formatType = getFormatType(dateInMillis)
        if (formatType == FORMAT_TYPE_TODAY) {
            val dateFormatted = context?.resources?.getString(R.string.date_format_util_today,
                    timeFormat.format(dateInMillis))
            return dateFormatted
        } else if (formatType == FORMAT_TYPE_TOMORROW) {
            val dateFormatted = context?.resources?.getString(R.string.date_format_util_tomorrow,
                    timeFormat.format(dateInMillis))
            return dateFormatted
        } else if (formatType == FORMAT_TYPE_YESTERDAY) {
            val dateFormatted = context?.resources?.getString(R.string.date_format_util_yesterday,
                    timeFormat.format(dateInMillis))
            return dateFormatted
        } else {
            val dateFormat = DateFormat.getLongDateFormat(context)
            return dateFormat.format(dateInMillis)
        }
    }

    /**
     * @param dateInMillis The millisecond value representing the date to check
     * *
     * @return True if the date is yesterday's date, else false.
     */
    fun getFormatType(dateInMillis: Long): Int {
        val timeToCheck = Calendar.getInstance()
        timeToCheck.timeInMillis = dateInMillis

        val checkYear = timeToCheck.get(Calendar.YEAR)
        val checkDay = timeToCheck.get(Calendar.DAY_OF_YEAR)

        timeToCheck.timeInMillis = System.currentTimeMillis()
        val currentDayNumber = timeToCheck.get(Calendar.DAY_OF_YEAR)
        val tomorrow = currentDayNumber + 1
        val yesterday = currentDayNumber - 1

        if (timeToCheck.get(Calendar.YEAR) != checkYear) return FORMAT_TYPE_OTHER
        if (checkDay == tomorrow) return FORMAT_TYPE_TOMORROW
        if (checkDay == currentDayNumber) return FORMAT_TYPE_TODAY
        if (checkDay == yesterday) return FORMAT_TYPE_YESTERDAY
        return FORMAT_TYPE_OTHER
    }
}

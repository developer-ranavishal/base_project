package com.example.demo_app.utils

import android.content.Context
import android.os.Build
import android.text.Html
import java.io.IOException


object Utils {
    fun getJsonDataFromAsset(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            ""
        }
    }

    fun getHtmlString(input: String): String {
        val description = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                input,
                Html.FROM_HTML_MODE_LEGACY
            ).toString()
        } else {
            Html.fromHtml(
                input
            ).toString()
        }
        return description
    }

    fun getCommentTimeGap(created: Long): String {
        val timeGap = System.currentTimeMillis() - created
        //Minutes
        val timeGapInMin = timeGap / (1000 * 60)
        val accurate = timeGapInMin - 570
        if (accurate < 60) {
            return "${accurate}m"
        }
        //Hour
        val timeGapInHr = accurate / 60
        if (timeGapInHr < 24) {
            return "${timeGapInHr}hr"
        }
        //Days
        val timeGapInDays = timeGapInHr / 24
        if (timeGapInDays < 7) {
            return "${timeGapInDays}d"
        }
        //Weeks
        val timeGapInWeeks = timeGapInDays / 7
        if (timeGapInWeeks < 7) {
            return "${timeGapInWeeks}w"
        }
        //Months
        val timeGapInMonths = timeGapInWeeks / 4
        if (timeGapInMonths < 12) {
            return "${timeGapInMonths}M"
        }
        val timeGapInYears = timeGapInMonths / 12
        return "${timeGapInYears}y"
    }

    fun getTimeDay(created: Long): String {
        val timeGap = System.currentTimeMillis() - created
        //Minutes
        val timeGapInMin = timeGap / (1000 * 60)
        //Hour
        val timeGapInHr = timeGapInMin / 60
        //Days
        val timeGapInDays = timeGapInHr / 24

        return timeGapInDays.toString()
    }

    fun getTimeGap(created: Long): String {
        val timeGap = System.currentTimeMillis() - created
        //Minutes
        val timeGapInMin = timeGap / (1000 * 60)
        // var accurate = timeGapInMin - 570
        if (timeGapInMin < 60) {
            return if (timeGapInMin.toInt() <= 1) {
                "Just now"
            } else {
                "$timeGapInMin minutes ago"
            }
        }
        //Hour
        val timeGapInHr = timeGapInMin / 60
        if (timeGapInHr < 24) {
            return if (timeGapInHr.toInt() == 1) {
                "an hour ago"
            } else {
                "$timeGapInHr hours ago"
            }
        }
        //Days
        val timeGapInDays = timeGapInHr / 24
        if (timeGapInDays < 7) {
            return if (timeGapInDays.toInt() == 1) {
                "a day ago"
            } else {
                "$timeGapInDays days ago"
            }
        }
        //Weeks
        val timeGapInWeeks = timeGapInDays / 7
        if (timeGapInWeeks < 7) {
            return if (timeGapInWeeks.toInt() == 1) {
                "a week ago"
            } else {
                "$timeGapInWeeks weeks ago"
            }
        }
        //Months
        val timeGapInMonths = timeGapInWeeks / 4
        if (timeGapInMonths < 12) {
            return if (timeGapInMonths.toInt() == 1) {
                "a month ago"
            } else {
                "$timeGapInMonths months ago"
            }
        }
        val timeGapInYears = timeGapInMonths / 12
        return if (timeGapInYears.toInt() == 1) {
            "a year ago"
        } else {
            "$timeGapInYears years ago"
        }
    }
}
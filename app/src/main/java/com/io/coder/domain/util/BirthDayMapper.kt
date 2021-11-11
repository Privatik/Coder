package com.io.coder.domain.util

import com.io.coder.domain.model.BirthDay
import java.text.SimpleDateFormat
import java.util.*


private var monthNames = arrayOf(
    "янв",
    "фев",
    "мар",
    "фпр",
    "май",
    "июн",
    "июл",
    "авг",
    "сен",
    "окт",
    "ноя",
    "дек"
)

fun getBirthDayFromString(dateSt: String): BirthDay?{
    return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).let { sdf ->
        sdf.parse(dateSt)?.let { date ->
            val cal = Calendar.getInstance()
            cal.time = date
            BirthDay(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH))
        }
    }
}

fun BirthDay.getStringDate(): String{
    return "$day ${monthNames[mount]}"
}
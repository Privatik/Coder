package com.io.coder.domain.util

import com.io.coder.domain.model.BirthDay
import com.io.coder.presentation.error_screen.model_parcelize.BirthDayParcelize
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.*
import java.util.concurrent.TimeUnit


private var monthNames = arrayOf(
    "января",
    "февраля",
    "марта",
    "апреля",
    "мая",
    "июня",
    "июля",
    "августа",
    "сенентября",
    "октября",
    "ноября",
    "декабря"
)

fun getBirthDayFromString(dateSt: String): BirthDay?{
    return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).let { sdf ->
        sdf.parse(dateSt)?.let { date ->
            val cal = Calendar.getInstance()
            cal.time = date
            BirthDay(
                day = cal.get(Calendar.DAY_OF_MONTH),
                mount = cal.get(Calendar.MONTH),
                year = cal.get(Calendar.YEAR)
            )
        }
    }
}

fun BirthDay.getStringDate(): String{
    return "$day ${monthNames[mount].take(3)}"
}

fun BirthDayParcelize.getStringFullDate(): String{
    return "$day ${monthNames[mount]} $year"
}

fun BirthDayParcelize.getAge(): Int{
    val dif = Calendar.getInstance().apply {
        add(Calendar.YEAR, -year)
        add(Calendar.MONTH, -mount)
        add(Calendar.DAY_OF_MONTH, -day)
    }

    return dif.get(Calendar.YEAR)
}

fun getYearTitle(
    oneYear: String,
    fromTwoToFourYear: String,
    elseYear: String,
    year: Int
): String{
    val lastNumber = year % 10
    return when (lastNumber) {
        1 -> {
            oneYear
        }
        in 2 until 5 -> {
            fromTwoToFourYear
        }
        else -> {
            elseYear
        }
    }
}
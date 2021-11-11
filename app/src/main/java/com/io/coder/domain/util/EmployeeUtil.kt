package com.io.coder.domain.util

import com.io.coder.domain.model.BirthDay
import com.io.coder.domain.model.Employee
import java.util.*

fun List<Employee>.tripleSortBirthDayAndNextYear(): Triple<List<Employee>,List<Employee>,Int?>{
    var year: Int? = null
    val currentDay = Calendar.getInstance().let {
        year = it.get(Calendar.YEAR) + 1
        BirthDay(
            day = it.get(Calendar.DAY_OF_MONTH),
            mount = it.get(Calendar.MONTH)
        )
    }
    val sortList = this.sortedWith(
        compareBy(
            {it.birthday.mount},
            {it.birthday.day}
        )
    )

    val index = sortList.indexOfFirst {
        currentDay.day < it.birthday.day && currentDay.mount <= it.birthday.mount
    }

    if (index == 0 || index == lastIndex){
        return Triple(sortList, emptyList(), null)
    }

    return Triple(
        sortList.subList(index + 1, lastIndex),
        sortList.subList(0, index + 1),
        year
    )
}
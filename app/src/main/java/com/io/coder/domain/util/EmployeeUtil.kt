package com.io.coder.domain.util

import com.io.coder.domain.model.BirthDay
import com.io.coder.domain.model.Employee
import com.io.coder.domain.state.Department
import java.util.*

fun List<Employee>.tripleSortBirthDayAndNextYear(): Triple<List<Employee>,List<Employee>,Int?>{
    var year: Int? = null
    val currentDay = Calendar.getInstance().let {
        year = it.get(Calendar.YEAR) + 1
        BirthDay(
            day = it.get(Calendar.DAY_OF_MONTH),
            mount = it.get(Calendar.MONTH),
            year = it.get(Calendar.YEAR)
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

fun List<Employee>.filterByDepartment(department: Department): List<Employee>{
    if (department == Department.ALL) return this
    return this.filter { it.department == department }
}

fun List<Employee>.filterBySearch(searchText: String): List<Employee>{
    var list = emptyList<Employee>()
    searchText.split(" +").forEach { str ->
        if (list.isEmpty()){
            list = this.filter {
                it.firstName.contains(str, true)
                        || it.lastName.contains(str, true)
                        || it.userTag.contains(str, true)
            }
        } else {
            list.filter {
                it.firstName.contains(str, true)
                        || it.lastName.contains(str, true)
                        || it.userTag.contains(str, true)
            }
        }
    }

    return list
}
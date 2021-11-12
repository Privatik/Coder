package com.io.coder.data.mapper

import com.io.coder.data.model.EmployeeResponse
import com.io.coder.domain.model.BirthDay
import com.io.coder.domain.model.Employee
import com.io.coder.domain.util.getBirthDayFromString

fun EmployeeResponse.toModel(): Employee =
    Employee(
        avatarUrl = this.avatarUrl,
        birthday = getBirthDayFromString(this.birthday) ?: BirthDay(0,0, 0),
        department = this.department.toModel(),
        firstName = this.firstName,
        id = this.id,
        lastName = this.lastName,
        phone = this.phone,
        position = this.position,
        userTag = this.userTag
    )
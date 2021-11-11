package com.io.coder.data.mapper

import com.io.coder.data.model.EmployeeResponse
import com.io.coder.domain.model.Employee

fun EmployeeResponse.toModel(): Employee =
    Employee(
        avatarUrl = this.avatarUrl,
        birthday = this.birthday,
        department = this.department.toModel(),
        firstName = this.firstName,
        id = this.id,
        lastName = this.lastName,
        phone = this.phone,
        position = this.position,
        userTag = this.userTag
    )
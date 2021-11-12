package com.io.coder.presentation.main_screen.model_parcelize

import android.os.Parcelable
import com.io.coder.domain.model.Employee
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeParcelize(
    val avatarUrl: String,
    val birthday: BirthDayParcelize,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
): Parcelable

fun Employee.toParcelize(): EmployeeParcelize =
    EmployeeParcelize(
        avatarUrl = this.avatarUrl,
        birthday = this.birthday.toParcelize(),
        department = this.department.title,
        firstName = this.firstName,
        id = this.id,
        lastName = this.lastName,
        phone = this.phone,
        position = this.position,
        userTag = this.userTag
    )
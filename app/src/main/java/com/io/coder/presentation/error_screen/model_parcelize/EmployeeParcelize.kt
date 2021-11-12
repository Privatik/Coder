package com.io.coder.presentation.error_screen.model_parcelize

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.io.coder.data.mapper.toModel
import com.io.coder.domain.model.BirthDay
import com.io.coder.domain.model.Employee
import com.io.coder.domain.state.Department
import com.io.coder.domain.util.getBirthDayFromString
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

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
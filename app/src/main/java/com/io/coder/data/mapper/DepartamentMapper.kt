package com.io.coder.data.mapper

import com.io.coder.data.model.DepartmentResponse
import com.io.coder.domain.state.Department

fun DepartmentResponse.toModel(): Department =
    when (name){
        "ANDROID" -> Department.ANDROID
        "IOS" -> Department.IOS
        "DESIGN" -> Department.DESIGN
        "MANAGEMENT" -> Department.MANAGEMENT
        "QA" -> Department.QA
        "BACK_OFFICE" -> Department.BACK_OFFICE
        "FRONTEND" -> Department.FRONTEND
        "HR" -> Department.HR
        "PR" -> Department.PR
        "BACKEND" -> Department.BACKEND
        "SUPPORT" -> Department.SUPPORT
        "ANALYTICS" -> Department.ANALYTICS
        else -> Department.ALL
    }
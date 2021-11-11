package com.io.coder.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DepartmentResponse {
    @SerialName("android") ANDROID,
    @SerialName("ios") IOS,
    @SerialName("design") DESIGN,
    @SerialName("management") MANAGEMENT,
    @SerialName("qa") QA,
    @SerialName("back_office") BACK_OFFICE,
    @SerialName("frontend") FRONTEND,
    @SerialName("hr") HR,
    @SerialName("pr") PR,
    @SerialName("backend") BACKEND,
    @SerialName("support") SUPPORT,
    @SerialName("analytics") ANALYTICS,
}
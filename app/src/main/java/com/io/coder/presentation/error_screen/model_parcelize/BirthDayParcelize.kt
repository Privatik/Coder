package com.io.coder.presentation.error_screen.model_parcelize

import android.os.Parcelable
import com.io.coder.domain.model.BirthDay
import kotlinx.parcelize.Parcelize

@Parcelize
data class BirthDayParcelize(
    val day: Int,
    val mount: Int,
    val year: Int
): Parcelable

fun BirthDay.toParcelize(): BirthDayParcelize =
    BirthDayParcelize(
        day = this.day,
        mount = this.mount,
        year = this.year
    )
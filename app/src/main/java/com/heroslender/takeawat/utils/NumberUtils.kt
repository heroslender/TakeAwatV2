package com.heroslender.takeawat.utils

import java.text.DecimalFormat

@Suppress("MemberVisibilityCanBePrivate")
object NumberUtils {
    val decimalFormat = DecimalFormat("0.00")

    fun format(value: Double): String {
        return decimalFormat.format(value)
    }
}
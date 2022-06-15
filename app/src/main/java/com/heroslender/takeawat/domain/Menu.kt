package com.heroslender.takeawat.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class Menu(
    @SerializedName("id")
    val id: Int,

    @SerializedName("date")
    val date: Date,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("halfPrice")
    val halfPrice: Double,

    @SerializedName("description")
    val description: String,
)
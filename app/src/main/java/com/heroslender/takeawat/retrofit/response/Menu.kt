package com.heroslender.takeawat.retrofit.response

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("halfPrice")
    val halfPrice: Double,

    @SerializedName("description")
    val description: String,
)

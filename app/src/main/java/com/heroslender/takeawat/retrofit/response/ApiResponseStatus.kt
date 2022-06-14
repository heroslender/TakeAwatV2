package com.heroslender.takeawat.retrofit.response

import com.google.gson.annotations.SerializedName

data class ApiResponseStatus(
    @SerializedName("status")
    val status: String,

    @SerializedName("timestamp")
    val timestamp: Long,

    @SerializedName("elapsed")
    val elapsed: Long,

    @SerializedName("errorCode")
    val errorCode: Int?,

    @SerializedName("errorMessage")
    val errorMessage: String?,
)

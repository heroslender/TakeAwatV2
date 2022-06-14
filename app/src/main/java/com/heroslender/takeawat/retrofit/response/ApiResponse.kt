package com.heroslender.takeawat.retrofit.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<out T>(
    @SerializedName("status")
    val status: ApiResponseStatus,

    @SerializedName("data")
    val data: T?,
)

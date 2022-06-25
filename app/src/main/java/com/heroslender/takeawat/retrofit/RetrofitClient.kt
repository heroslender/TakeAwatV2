package com.heroslender.takeawat.retrofit

import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.retrofit.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface RetrofitClient {

    @GET("/takeawat/v1/menu")
    suspend fun getMenu(@Query("date") date: Date): ApiResponse<List<Menu>>

    @GET("/takeawat/v1/menu")
    suspend fun getMenus(): ApiResponse<Map<Date, List<Menu>>>
}
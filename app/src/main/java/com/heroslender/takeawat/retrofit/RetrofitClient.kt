package com.heroslender.takeawat.retrofit

import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.retrofit.result.Result
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface RetrofitClient {

    @GET("/takeawat/v1/menu/{date}")
    suspend fun getMenu(@Path("date") date: Date): Result<List<Menu>>

    @GET("/takeawat/v1/menu")
    suspend fun getMenus(): Result<Map<Date, List<Menu>>>
}
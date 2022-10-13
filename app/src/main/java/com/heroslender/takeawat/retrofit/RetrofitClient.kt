package com.heroslender.takeawat.retrofit

import com.heroslender.takeawat.domain.Menu
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface RetrofitClient {

    @GET("/takeawat/v1/menu/{date}")
    suspend fun getMenu(@Path("date") date: Date): List<Menu>

    @GET("/takeawat/v1/menu")
    suspend fun getMenus(): Map<Date, List<Menu>>
}
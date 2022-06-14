package com.heroslender.takeawat.retrofit

import com.heroslender.takeawat.retrofit.response.ApiResponse
import com.heroslender.takeawat.retrofit.response.Menu
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface RetrofitClient {

    @GET("/v1/menu")
    fun getMenu(@Query("date") date: Date): Observable<ApiResponse<List<Menu>>>

    @GET("/v1/menu")
    fun getMenus(): Observable<ApiResponse<Map<Date, List<Menu>>>>
}
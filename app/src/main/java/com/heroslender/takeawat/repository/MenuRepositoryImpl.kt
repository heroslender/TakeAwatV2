package com.heroslender.takeawat.repository

import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.retrofit.RetrofitClient
import io.reactivex.rxjava3.core.Observable
import java.util.*

class MenuRepositoryImpl(
    private val retrofitClient: RetrofitClient,
) : MenuRepository {
    override fun getMenus(date: Date): Observable<DataState<List<Menu>>> {
        return retrofitClient.getMenu(date).map { response ->
            return@map if (response.status.errorCode != null) {
                DataState.error(response.status.errorMessage!!)
            } else if (response.status.status.toInt() !in 200..219) {
                DataState.error("Unknown error with status code " + response.status.status)
            } else {
                DataState.success(response.data!!)
            }
        }
    }

    override fun getMenus(): Observable<DataState<Map<Date, List<Menu>>>> {
        return retrofitClient.getMenus().map { response ->
            return@map if (response.status.errorCode != null) {
                DataState.error(response.status.errorMessage!!)
            } else if (response.status.status.toInt() !in 200..219) {
                DataState.error("Unknown error with status code " + response.status.status)
            } else {
                DataState.success(response.data!!)
            }
        }
    }
}
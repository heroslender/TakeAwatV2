package com.heroslender.takeawat.repository

import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

class MenuRepositoryImpl(
    private val retrofitClient: RetrofitClient,
) : MenuRepository {
    override fun getMenus(date: Date): Flow<DataState<List<Menu>>> = flow {
        val response = retrofitClient.getMenu(date)

        val value = if (response.status.errorCode != null) {
            DataState.error(response.status.errorMessage!!)
        } else if (response.status.status.toInt() !in 200..219) {
            DataState.error("Unknown error with status code " + response.status.status)
        } else {
            DataState.success(response.data!!)
        }

        emit(value)
    }.flowOn(Dispatchers.IO)

    override fun getMenus(): Flow<DataState<Map<Date, List<Menu>>>> = flow {
        val response = retrofitClient.getMenus()

        val value = if (response.status.errorCode != null) {
            DataState.error(response.status.errorMessage!!)
        } else if (response.status.status.toInt() !in 200..219) {
            DataState.error("Unknown error with status code " + response.status.status)
        } else {
            DataState.success(response.data!!.toSortedMap())
        }

        emit(value)
    }.flowOn(Dispatchers.IO)
}
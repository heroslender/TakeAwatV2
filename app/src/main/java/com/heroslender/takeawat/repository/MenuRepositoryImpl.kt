package com.heroslender.takeawat.repository

import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.repository.database.MenuDao
import com.heroslender.takeawat.repository.mapper.toMenu
import com.heroslender.takeawat.repository.mapper.toMenuEntity
import com.heroslender.takeawat.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

class MenuRepositoryImpl(
    private val menuDao: MenuDao,
    private val retrofitClient: RetrofitClient,
) : MenuRepository {
    override fun getMenus(date: Date): Flow<DataState<List<Menu>>> = flow {
        println(1)
        // Send cached data
        val findByDate = menuDao.findByDate(date.time).map { it.toMenu() }
        println(2)
        emit(DataState.success(findByDate))

        println(3)
        // Update data from the API
        val response = retrofitClient.getMenu(date)
        println(4)
        println("Response: $response")
        val value = if (response.status.errorCode != null) {
            DataState.error(response.status.errorMessage!!)
        } else if (response.status.status.toInt() !in 200..219) {
            DataState.error("Unknown error with status code " + response.status.status)
        } else {
            menuDao.insertAll(*response.data!!.map { it.toMenuEntity() }.toTypedArray())
            DataState.success(response.data)
        }

        emit(value)
    }.flowOn(Dispatchers.IO)

    override fun getDates(): Flow<DataState<List<Date>>> = flow<DataState<List<Date>>> {
        // Send cached data
        val dates = menuDao.getAllDates().map { timestamp -> Date(timestamp) }
        emit(DataState.success(dates))

        // Update data from the API
        val response = retrofitClient.getMenus()
        val value = if (response.status.errorCode != null) {
            DataState.error(response.status.errorMessage!!)
        } else if (response.status.status.toInt() !in 200..219) {
            DataState.error("Unknown error with status code " + response.status.status)
        } else {
            DataState.success(response.data!!.keys.toList().sorted())
        }

        emit(value)
    }.flowOn(Dispatchers.IO)
}
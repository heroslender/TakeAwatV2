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
import java.net.ConnectException
import java.util.*
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuDao: MenuDao,
    private val retrofitClient: RetrofitClient,
) : MenuRepository {
    override fun getMenus(date: Date): Flow<DataState<List<Menu>>> = flow {
        // Send cached data
        val findByDate = menuDao.findByDate(date.time).map { it.toMenu() }
        emit(DataState.success(findByDate))

        // Update data from the API
        val response = retrofitClient.getMenu(date)
        println("Response: $response")
        menuDao.insertAll(*response.map { it.toMenuEntity() }.toTypedArray())

        emit(DataState.success(response))
    }.flowOn(Dispatchers.IO)

    override fun getDates(): Flow<DataState<List<Date>>> = flow {
        // Send cached data
        val dates = menuDao.getAllDates().map { timestamp -> Date(timestamp) }
        emit(DataState.success(dates))

        // Update data from the API
        try {
            val response = retrofitClient.getMenus()
            emit(DataState.success(response.keys.toList().sorted()))
        } catch (e: ConnectException) {
            emit(DataState.error("Failed to connect to the remote server."))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(DataState.error("Something went wrong!"))
        }
    }.flowOn(Dispatchers.IO)
}
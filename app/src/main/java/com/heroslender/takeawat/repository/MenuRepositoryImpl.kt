package com.heroslender.takeawat.repository

import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.repository.database.MenuDao
import com.heroslender.takeawat.repository.mapper.toMenu
import com.heroslender.takeawat.repository.mapper.toMenuEntity
import com.heroslender.takeawat.retrofit.RetrofitClient
import com.heroslender.takeawat.retrofit.result.NetworkError
import com.heroslender.takeawat.retrofit.result.RemoteServiceHttpError
import com.heroslender.takeawat.retrofit.result.Result
import com.heroslender.takeawat.retrofit.result.UnexpectedError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
        val menuResult = retrofitClient.getMenu(date)
        if (menuResult is Result.Failure) {
            handleRetrofitFailure<List<Menu>>(menuResult)
            return@flow
        }

        val response = (menuResult as Result.Success).data
        menuDao.insertAll(*response.map { it.toMenuEntity() }.toTypedArray())

        emit(DataState.success(response))
    }.flowOn(Dispatchers.IO)

    override fun getDates(): Flow<DataState<List<Date>>> = flow {
        // Send cached data
        val dates = menuDao.getAllDates().map { timestamp -> Date(timestamp) }
        emit(DataState.success(dates))

        // Update data from the API
        val menusResult = retrofitClient.getMenus()
        if (menusResult is Result.Failure) {
            handleRetrofitFailure<List<Date>>(menusResult)
            return@flow
        }

        val response = (menusResult as Result.Success).data
        emit(DataState.success(response.keys.toList().sorted()))

        for (list in response.values) {
            menuDao.insertAll(*list.map { it.toMenuEntity() }.toTypedArray())
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun <T> FlowCollector<DataState<T>>.handleRetrofitFailure(failure: Result.Failure) {
        when (val error = failure.error) {
            is NetworkError,
            is UnexpectedError -> emit(DataState.error(error.message ?: "Something went wrong!"))
            is RemoteServiceHttpError -> emit(DataState.error("Received response with code ${error.httpStatusCode}!"))
        }
    }
}
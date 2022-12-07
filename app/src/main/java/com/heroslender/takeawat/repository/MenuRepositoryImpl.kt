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
import kotlinx.coroutines.flow.*
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

        emitAll(fetchMenus(date))
    }.flowOn(Dispatchers.IO)

    override fun fetchMenus(date: Date): Flow<DataState<List<Menu>>> = flow<DataState<List<Menu>>> {
        // Update data from the API
        val menuResult = retrofitClient.getMenu(date)
        if (menuResult is Result.Failure) {
            handleRetrofitFailure(menuResult)
            return@flow
        }

        val response = (menuResult as Result.Success).data
        emit(DataState.success(response))

        menuDao.delete(date.time)
        menuDao.insertAll(response.map { it.toMenuEntity() })
    }.flowOn(Dispatchers.IO)

    override fun getDates(): Flow<DataState<List<Date>>> = flow {
        // Send cached data
        val dates = menuDao.getAllDates().map { timestamp -> Date(timestamp) }
        emit(DataState.success(dates))

        // Update cached data
        emitAll(fetchDates())
    }.flowOn(Dispatchers.IO)

    override fun fetchDates(): Flow<DataState<List<Date>>> = flow<DataState<List<Date>>> {
        // Update data from the API
        val menusResult = retrofitClient.getMenus()
        if (menusResult is Result.Failure) {
            handleRetrofitFailure(menusResult)
            return@flow
        }

        val response = (menusResult as Result.Success).data
        emit(DataState.success(response.keys.toList().sorted()))

        menuDao.delete()
        for (list in response.values) {
            menuDao.insertAll(list.map { it.toMenuEntity() })
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
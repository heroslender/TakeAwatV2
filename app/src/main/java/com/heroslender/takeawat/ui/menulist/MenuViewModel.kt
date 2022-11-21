package com.heroslender.takeawat.ui.menulist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.repository.DataState
import com.heroslender.takeawat.repository.MenuRepository
import com.heroslender.takeawat.utils.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _failure = MutableLiveData<Failure>()
    val failure: LiveData<Failure>
        get() = _failure

    private val _menus: MutableLiveData<List<Menu>> = MutableLiveData()
    val menus: LiveData<List<Menu>>
        get() = _menus

    private val _dates: MutableLiveData<List<Date>> = MutableLiveData()
    val dates: LiveData<List<Date>>
        get() = _dates

    private var selectedDate: Date = Date()
    private val _date: MutableLiveData<Date> = MutableLiveData(Date())
    val date: LiveData<Date>
        get() = _date

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            menuRepository.getDates()
                .handleException()
                .collectDates()

            menuRepository.getMenus(selectedDate)
                .handleException()
                .collectMenus()
        }
    }

    fun setDate(date: Date) {
        selectedDate = date

        viewModelScope.launch {
            menuRepository.getMenus(date)
                .handleException()
                .collectMenus()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            menuRepository.fetchDates()
                .handleException()
                .collectDates()

            menuRepository.fetchMenus(selectedDate)
                .handleException()
                .collectMenus()
        }
    }

    private suspend fun Flow<DataState<List<Date>>>.collectDates() = collect { dataState ->
        when (dataState) {
            is DataState.Success -> {
                val dates = dataState.data
                _dates.postValue(dates)

                if (!dates.contains(selectedDate)) {
                    val nearestDate = if (dates.isEmpty()) Date() else dates.getNearestDate()

                    setDate(nearestDate)
                    _date.postValue(nearestDate)
                }
            }
            is DataState.Error -> _failure.postValue(Failure(dataState.error))
        }
    }

    private suspend fun Flow<DataState<List<Menu>>>.collectMenus() = collect { dataState ->
        when (dataState) {
            is DataState.Success -> _menus.postValue(dataState.data)
            is DataState.Error -> _failure.postValue(Failure(dataState.error))
        }
    }

    private fun <T> Flow<T>.handleException(): Flow<T> = catch { throwable ->
        Log.i("MenuViewModel", throwable.message ?: "")
        _failure.postValue(Failure(throwable.message ?: "Unknown error"))
    }

    private fun List<Date>.getNearestDate(): Date {
        if (isEmpty()) {
            throw IllegalArgumentException("Date list is empty")
        }

        val now = Date()
        var distance: Long = Long.MAX_VALUE
        var date: Date = now
        for (d in this) {
            val newDistance = d.time - date.time
            val absDistance = abs(newDistance)
            if (absDistance < distance || (absDistance == distance && newDistance > 0)) {
                distance = absDistance
                date = d
            }
        }

        return date
    }
}
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

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

    fun fetchMenus(date: Date = Date()) {
        viewModelScope.launch {
            menuRepository.getMenus(date)
                .catch { throwable ->
                    Log.i("MenuViewModel", throwable.message ?: "")
                    _failure.postValue(Failure(throwable.message ?: "Unknown error"))
                }
                .collect { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            val data = dataState.data
                            _menus.postValue(data)
                        }
                        is DataState.Error -> _failure.postValue(Failure(dataState.error))
                    }
                }
        }
    }

    fun fetchDates() {
        viewModelScope.launch {
            menuRepository.getDates()
                .catch { throwable ->
                    Log.i("MenuViewModel1", throwable.message ?: "")
                    _failure.postValue(Failure(throwable.message ?: "Unknown error"))
                }
                .collect { dataState ->
                    when (dataState) {
                        is DataState.Success -> _dates.postValue(dataState.data)
                        is DataState.Error -> _failure.postValue(Failure(dataState.error))
                    }
                }
        }
    }
}
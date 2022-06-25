package com.heroslender.takeawat.ui.menulist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.repository.DataState
import com.heroslender.takeawat.repository.MenuRepository
import com.heroslender.takeawat.repository.MenuRepositoryImpl
import com.heroslender.takeawat.retrofit.RetrofitServiceGenerator
import com.heroslender.takeawat.utils.Failure
import java.util.*

class MenuViewModel(application: Application) : AndroidViewModel(application) {
    private val menuRepository: MenuRepository =
        MenuRepositoryImpl(RetrofitServiceGenerator.getClient(getApplication()))

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
        menuRepository.getMenus(date)
            .subscribe(
                { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            val data = dataState.data
                            _menus.postValue(data)
                        }
                        is DataState.Error -> _failure.postValue(Failure(dataState.error))
                    }
                }, { throwable ->
                    Log.i("MenuViewModel", throwable.message ?: "")
                }
            )
    }

    fun fetchDates() {
        menuRepository.getMenus()
            .subscribe(
                { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            val data = dataState.data
                            _dates.postValue(data.mapNotNull { it.value.firstOrNull()?.date })
                        }
                        is DataState.Error -> _failure.postValue(Failure(dataState.error))
                    }
                }, { throwable ->
                    Log.i("MenuViewModel1", throwable.message ?: "")
                }
            )
    }
}
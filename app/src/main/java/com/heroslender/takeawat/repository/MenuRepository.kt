package com.heroslender.takeawat.repository

import com.heroslender.takeawat.domain.Menu
import kotlinx.coroutines.flow.Flow
import java.util.*

interface MenuRepository {
    fun getMenus(date: Date): Flow<DataState<List<Menu>>>

    fun getDates(): Flow<DataState<List<Date>>>

    fun fetchMenus(date: Date): Flow<DataState<List<Menu>>>

    fun fetchDates(): Flow<DataState<List<Date>>>
}
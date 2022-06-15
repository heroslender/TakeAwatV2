package com.heroslender.takeawat.repository

import com.heroslender.takeawat.domain.Menu
import io.reactivex.rxjava3.core.Observable
import java.util.*

interface MenuRepository {
    fun getMenus(date: Date): Observable<DataState<List<Menu>>>

    fun getMenus(): Observable<DataState<Map<Date, List<Menu>>>>
}
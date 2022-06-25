package com.heroslender.takeawat.repository

import com.heroslender.takeawat.blockingFirst
import com.heroslender.takeawat.retrofit.BaseRetrofitTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MenuRepositoryTest : BaseRetrofitTest() {

    lateinit var repository: MenuRepositoryImpl

    @Before
    fun setUp() {
        repository = MenuRepositoryImpl(retrofitClient)
    }

    @Test
    fun `fetch menu and check success DataState`() {
        enqueueResponse("get_date_success_response.json")

        val response = repository.getMenus(Date()).blockingFirst()

        assertTrue(response is DataState.Success)
    }

    @Test
    fun `fetch menu and check valid data`() {
        enqueueResponse("get_date_success_response.json")

        val response = repository.getMenus(Date()).blockingFirst()

        val data = (response as DataState.Success).data
        assertEquals(3, data.size)
        assertEquals(1, data[0].id)
        assertEquals("Menu 1", data[0].name)
        assertEquals(10.0, data[0].price)
        assertEquals("Menu 1 Description", data[0].description)
    }

    @Test
    fun `fetch menu and check error DataState`() {
        enqueueResponse("get_date_failed_response.json")

        val response = repository.getMenus(Date()).blockingFirst()

        assertTrue(response is DataState.Error)
    }

    @Test
    fun `fetch menu and check expected error data`() {
        enqueueResponse("get_date_failed_response.json")

        val response = repository.getMenus(Date()).blockingFirst()

        assertEquals("Something went wrong", (response as DataState.Error).error)
    }
}
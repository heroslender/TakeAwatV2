package com.heroslender.takeawat.repository

import com.heroslender.takeawat.blockingLast
import com.heroslender.takeawat.repository.database.MenuDaoFake
import com.heroslender.takeawat.retrofit.BaseRetrofitTest
import com.heroslender.takeawat.retrofit.result.HttpStatus
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MenuRepositoryTest : BaseRetrofitTest() {

    lateinit var repository: MenuRepositoryImpl

    @Before
    fun setUp() {
        repository = MenuRepositoryImpl(MenuDaoFake(), retrofitClient)
    }

    @Test
    fun `fetch menu and check success DataState`() {
        enqueueResponse("get_date_success_response.json")

        val response = repository.getMenus(Date()).blockingLast()

        assertTrue(response is DataState.Success)
    }

    @Test
    fun `fetch menu and check valid data`() {
        enqueueResponse("get_date_success_response.json")

        val response = repository.getMenus(Date()).blockingLast()

        val data = (response as DataState.Success).data
        assertEquals(3, data.size)
        assertEquals(1, data[0].id)
        assertEquals("Menu 1", data[0].name)
        assertEquals(10.0, data[0].price)
        assertEquals("Menu 1 Description", data[0].description)
    }

    @Test
    fun `fetch menu and check error DataState`() {
        enqueueResponse("get_date_failed_response.json", HttpStatus.BAD_REQUEST)

        val response = repository.getMenus(Date()).blockingLast()

        assertTrue(response is DataState.Error)
    }

    @Test
    fun `fetch menu and check expected error data`() {
        enqueueResponse("get_date_failed_response.json", HttpStatus.BAD_REQUEST)

        val response = repository.getMenus(Date()).blockingLast()

        assertEquals(
            "Received response with code ${HttpStatus.BAD_REQUEST.value}!",
            (response as DataState.Error).error
        )
    }
}
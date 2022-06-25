package com.heroslender.takeawat.retrofit

import com.heroslender.takeawat.mockFileRead
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class RetrofitClientTest : BaseRetrofitTest() {

    @Test
    fun `check mock file read returns something`() {
        val content = mockFileRead("get_date_success_response.json")
        assertTrue(content.isNotEmpty())
    }

    @Test
    fun `fetch menu and check response Code 200 returned`() {
        enqueueResponse("get_date_success_response.json")

        val actualResponse = runBlocking { retrofitClient.getMenu(Date()) }

        assertTrue(actualResponse.status.status.contains("200"))
        assertNull(actualResponse.status.errorCode)
    }

    @Test
    fun `fetch menu and check expected response`() {
        enqueueResponse("get_date_success_response.json")

        val res = runBlocking { retrofitClient.getMenu(Date()) }

        assertNotNull(res.data)

        val data = res.data!!
        assertEquals(3, data.size)
        assertEquals(1, data[0].id)
        assertEquals("Menu 1", data[0].name)
        assertEquals(10.0, data[0].price)
        assertEquals("Menu 1 Description", data[0].description)
    }

    @Test
    fun `fetch menu and check for failed response`() {
        enqueueResponse("get_date_failed_response.json")

        val res = runBlocking { retrofitClient.getMenu(Date()) }

        assertEquals("500", res.status.status)
        assertNotNull(res.status.errorCode)
        assertEquals("Something went wrong", res.status.errorMessage)
    }
}
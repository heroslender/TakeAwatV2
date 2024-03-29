package com.heroslender.takeawat.retrofit

import com.heroslender.takeawat.mockFileRead
import com.heroslender.takeawat.retrofit.result.HttpStatus
import com.heroslender.takeawat.retrofit.result.RemoteServiceHttpError
import com.heroslender.takeawat.retrofit.result.Result
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
    fun `fetch menu and check expected response`() {
        enqueueResponse("get_date_success_response.json")

        val res = runBlocking { retrofitClient.getMenu(Date()) }

        assertNotNull(res)
        assertInstanceOf(Result.Success::class.java, res)

        val data = (res as Result.Success).data
        assertEquals(3, data.size)
        assertEquals(1, data[0].id)
        assertEquals("Menu 1", data[0].name)
        assertEquals(10.0, data[0].price)
        assertEquals("Menu 1 Description", data[0].description)
    }

    @Test
    fun `fetch menu and check for failed response`() {
        enqueueResponse("get_date_failed_response.json", HttpStatus.BAD_REQUEST)

        val res = runBlocking { retrofitClient.getMenu(Date()) }
        assertInstanceOf(Result.Failure::class.java, res)

        val failure = res as Result.Failure
        assertInstanceOf(RemoteServiceHttpError::class.java, failure.error)

        val httpError = failure.error as RemoteServiceHttpError
        assertEquals(httpError.httpStatusCode, HttpStatus.BAD_REQUEST.value)
    }
}
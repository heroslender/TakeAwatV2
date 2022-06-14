package com.heroslender.takeawat.retrofit

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class RetrofitClientTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        retrofitClient = RetrofitServiceGenerator.generate(
            RetrofitClient::class.java,
            mockWebServer.url("/").toString(),
            null
        )
    }

    @Test
    fun `check mock file read returns something`() {
        val content = mockFileRead("get_date_success_response.json")
        assertNotNull(content)
    }

    @Test
    fun `fetch menu and check response Code 200 returned`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockFileRead("get_date_success_response.json"))
        mockWebServer.enqueue(response)

        val actualResponse = retrofitClient.getMenu(Date()).blockingFirst()

        assertEquals(
            response.toString().contains("200"),
            actualResponse.status.status.contains("200")
        )
        assertNull(actualResponse.status.errorCode)
    }

    @Test
    fun `fetch menu and check expected response`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockFileRead("get_date_success_response.json"))
        mockWebServer.enqueue(response)

        val res = retrofitClient.getMenu(Date()).blockingFirst()

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
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockFileRead("get_date_failed_response.json"))
        mockWebServer.enqueue(response)

        val res = retrofitClient.getMenu(Date()).blockingFirst()

        assertEquals("500", res.status.status)
        assertNotNull(res.status.errorCode)
        assertEquals("Something went wrong", res.status.errorMessage)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
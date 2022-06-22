package com.heroslender.takeawat.retrofit

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import java.net.HttpURLConnection

open class BaseRetrofitTest {
    lateinit var mockWebServer: MockWebServer

    lateinit var retrofitClient: RetrofitClient

    @Before
    fun onBefore() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        retrofitClient = RetrofitServiceGenerator.generate(
            RetrofitClient::class.java,
            mockWebServer.url("/").toString(),
            null
        )
    }

    @After
    fun onAfter() {
        mockWebServer.shutdown()
    }

    fun enqueueResponse(fileName: String, statusCode: Int = HttpURLConnection.HTTP_OK) {
        println("Enqueuing response...")
        val response = MockResponse()
            .setResponseCode(statusCode)
            .setBody(mockFileRead(fileName))

        mockWebServer.enqueue(response)
    }
}
package com.heroslender.takeawat.retrofit

import com.heroslender.takeawat.mockFileRead
import com.heroslender.takeawat.retrofit.result.HttpStatus
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

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

    fun enqueueResponse(fileName: String, statusCode: HttpStatus = HttpStatus.OK) {
        val response = MockResponse()
            .setResponseCode(statusCode.value)
            .setBody(mockFileRead(fileName))

        mockWebServer.enqueue(response)
    }
}
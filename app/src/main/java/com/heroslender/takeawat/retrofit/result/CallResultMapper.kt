package com.heroslender.takeawat.retrofit.result

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Maps a [Call] to [CallResultMapper].
 * */
internal class CallResultMapper<T>(
    private val delegate: Call<T>,
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (call.isCanceled) return

                val result: Result<T> = if (response.isSuccessful) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Failure(RemoteServiceHttpError(response))
                }

                callback.onResponse(this@CallResultMapper, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val error: Error = when (t) {
                    is IOException -> NetworkError()
                    is HttpException -> UnexpectedError("Something went wrong!", t)
                    else -> UnexpectedError(
                        t.localizedMessage ?: t.message ?: "Unexpected error",
                        t
                    )
                }

                callback.onResponse(
                    this@CallResultMapper,
                    Response.success(Result.Failure(error))
                )
            }
        })
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun execute(): Response<Result<T>> {
        return Response.success(Result.Success(delegate.execute().body()!!))
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun clone(): Call<Result<T>> {
        return CallResultMapper(delegate.clone())
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }

}
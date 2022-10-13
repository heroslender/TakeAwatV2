package com.heroslender.takeawat.retrofit.result

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CallResultAdapterFactory : CallAdapter.Factory() {
    companion object {
        fun create(): CallResultAdapterFactory {
            return CallResultAdapterFactory()
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType) {
            return null
        }

        val upperBound = getParameterUpperBound(0, returnType)
        return if (upperBound is ParameterizedType && upperBound.rawType == Result::class.java) {
            CallResultAdapter<Any>(getParameterUpperBound(0, upperBound))
        } else {
            null
        }
    }

    private class CallResultAdapter<R> constructor(
        private val responseType: Type,
    ) : CallAdapter<R, Call<Result<R>>> {

        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: Call<R>): Call<Result<R>> {
            return CallResultMapper(call)
        }
    }
}
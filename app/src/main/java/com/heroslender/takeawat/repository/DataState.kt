package com.heroslender.takeawat.repository

sealed interface DataState<T> {
    data class Success<T>(val data: T) : DataState<T>
    data class Error<T>(val error: String) : DataState<T>

    companion object {
        fun <T> success(data: T): DataState<T> = Success(data)
        fun <T> error(error: String): DataState<T> = Error(error)
    }
}
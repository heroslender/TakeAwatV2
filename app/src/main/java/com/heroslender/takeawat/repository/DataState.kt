package com.heroslender.takeawat.repository

sealed interface DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>
    data class Error<T>(val error: String) : DataState<T>

    companion object {
        fun <T> success(data: T): DataState<T> = Success(data)
        fun <T> error(error: String): DataState<T> = Error(error)
    }
}

inline fun <T, R> DataState<T>.map(op: T.() -> R): DataState<R> {
    return when (this) {
        is DataState.Success -> op.invoke(data).let { mapped -> DataState.success(mapped) }
        is DataState.Error -> return DataState.error(error)
    }
}
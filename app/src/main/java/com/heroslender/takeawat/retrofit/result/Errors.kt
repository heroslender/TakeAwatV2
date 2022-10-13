package com.heroslender.takeawat.retrofit.result

import retrofit2.Response

class UnexpectedError(
    override val message: String = "Unexpected Error",
    override val cause: Throwable? = null
) : Error() {
    override fun toString(): String {
        return message
    }
}

/**
 * Represents an error in which the server could not be reached.
 */
class NetworkError : Error("No internet connection") {
    override fun toString(): String {
        return "NetworkError"
    }
}

/**
 * An error response from the server.
 */
open class RemoteServiceError : Error() {
    override fun toString(): String {
        return "RemoteServiceError(message: $message)"
    }
}

/**
 * A Remote Service Error with a HttpStatus code.
 */
open class RemoteServiceHttpError(val response: Response<*>) : RemoteServiceError() {
    val httpStatusCode = response.code()
    val httpStatus: HttpStatus? = HttpStatus.resolve(httpStatusCode)

    override fun toString(): String {
        return "RemoteServiceHttpError" +
                "\ncode: $httpStatusCode (${httpStatus?.reasonPhrase ?: "Unknown"})" +
                "\nmessage: ${super.message}"
    }
}